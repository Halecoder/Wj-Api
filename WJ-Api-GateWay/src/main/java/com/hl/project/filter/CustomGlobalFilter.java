package com.hl.project.filter;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hl.model.InvokeInterfaceRequest;
import com.hl.project.model.entity.InterfaceInfo;
import com.hl.project.model.entity.User;
import com.hl.project.service.InnerInterfaceInfoService;
import com.hl.project.service.InnerUserInterfaceInfoService;
import com.hl.project.service.InnerUserService;
import com.hl.project.service.RankService;
import com.hl.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 全局过滤器
 *
 * @version 1.0
 * @author: HL
 * @date: 2023/2/1
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Reference(version = "${dubbo.service.version}", check = false)
    private InnerUserService innerUserService;

    @Reference(version = "${dubbo.service.version}", check = false)
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @Reference(version = "${dubbo.service.version}", check = false)
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @Reference(version = "${dubbo.service.version}", check = false)
    private RankService rankService;

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1", "127.0.0.2","0:0:0:0:0:0:0:1");

    private static final long FIVE_MINUTES = 5 * 60 * 1000L;

//    private static final String INTERFACE_HOST = "http://localhost:8080";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //排除不需要过滤的请求路径
        String path = exchange.getRequest().getURI().getPath();
        if (path.contains("/api-docs") || path.contains("/v3/api-docs") || path.contains("/doc.html")) {
            return chain.filter(exchange);
        }

        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        String method = Objects.requireNonNull(request.getMethod()).toString();
        log.info("请求id: {}", request.getId());
        log.info("请求方法: {}", method);
        log.info("请求参数: {}", request.getQueryParams());
        log.info("请求体: {}", request.getBody());
        log.info("请求头: {}", request.getHeaders());
        String remoteAddress = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        log.info("请求地址: {}", remoteAddress);

        // 2. 访问控制 - 黑白名单
        ServerHttpResponse response = exchange.getResponse();
        if (!IP_WHITE_LIST.contains(remoteAddress)) {
            return handleNoAuth(response);
        }

        // 3. 用户鉴权
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        // 防止中文乱码
        String body = null;
        try {
            body = URLDecoder.decode(headers.getFirst("body"), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

//        String host = getHost(body, method);
//
//        if (StrUtil.isBlank(host)) {
//            return handleInvokeError(response);
//        }
//        //获取请求头
//        String path = host + request.getPath().value();

        String sign = headers.getFirst("sign");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判断是否有空
        if (hasBlank) {
            return handleInvokeError(response);
        }
        // 使用accessKey去数据库查询secretKey
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
        }
        if (invokeUser == null) {
            return handleInvokeError(response);
        }
        String secretKey = invokeUser.getSecretKey();
        String sign1 = SignUtils.genSign(body, secretKey);

        if (!StrUtil.equals(sign, sign1)) {
            return handleInvokeError(response);
        }
        // TODO 判断随机数nonce
        // 时间戳是否为数字
        if (!NumberUtil.isNumber(timestamp)) {
            return handleInvokeError(response);
        }
        // 五分钟内的请求有效
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > FIVE_MINUTES) {
            return handleInvokeError(response);
        }
        //body是json字符串 转换成对象
        InvokeInterfaceRequest invokeInterfaceRequest = JSONUtil.toBean(body, InvokeInterfaceRequest.class);
        //根据id查询接口信息
        // 4. 请求的模拟接口是否存在
        InterfaceInfo invokeInterfaceInfo = null;
        try {
            invokeInterfaceInfo = innerInterfaceInfoService.getInvokeInterfaceInfoById(invokeInterfaceRequest.getId(), method);
        } catch (Exception e) {
            log.error("getInvokeInterfaceInfo error", e);
        }
        if (invokeInterfaceInfo == null) {
            return handleInvokeError(response);
        }
        //TODO 暂时测试关闭  是否有调用次数
//        if (!innerUserInterfaceInfoService.hasInvokeNum(invokeUser.getId(), invokeInterfaceInfo.getId())) {
//            return handleInvokeError(response);
//        }
        // 5. 请求转发，调用模拟接口
        return handleResponse(exchange, chain, invokeUser.getId(), invokeInterfaceInfo.getId());

    }

    @Override
    public int getOrder() {
        return -3;
    }

    /**
     * 处理响应
     *
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long userId, long interfaceInfoId) {
        try {
            // 从交换机拿到原始response
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓冲区工厂 拿到缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到状态码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                // 装饰，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 等调用完转发的接口后才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        // 对象是响应式的
                        if (body instanceof Flux) {
                            // 我们拿到真正的body
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里面写数据
                            // 拼接字符串
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7. 调用成功，接口调用次数+1
                                try {
                                    innerUserInterfaceInfoService.invokeInterfaceCount(userId, interfaceInfoId);
                                    rankService.zIncreamentScore(innerUserInterfaceInfoService.getInterfaceNameById(interfaceInfoId),1);
                                } catch (Exception e) {
                                    log.error("invokeInterfaceCount error", e);
                                }
                                // data从这个content中读取
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);// 释放掉内存
                                // 6.构建日志
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);// data
                                rspArgs.add(data);
                                log.info("<--- status:{} data:{}"// data
                                        , rspArgs.toArray());// log.info("<-- {} {}", originalResponse.getStatusCode(), data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            // 8.调用失败返回错误状态码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);// 降级处理返回数据
        } catch (Exception e) {
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }

    }

    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    //获取服务地址
    private String getHost(String body, String method) {
        //body是json字符串 转换成对象
        InvokeInterfaceRequest invokeInterfaceRequest = JSONUtil.toBean(body, InvokeInterfaceRequest.class);
        //根据id查询接口信息
        InterfaceInfo invokeInterfaceInfo = null;
try {
            invokeInterfaceInfo = innerInterfaceInfoService.getInvokeInterfaceInfoById(invokeInterfaceRequest.getId(), method);
        } catch (Exception e) {
            log.error("getInvokeInterfaceInfo error", e);
        }
        if (invokeInterfaceInfo == null) {
            return null;
        }
        return invokeInterfaceInfo.getHost();

    }
}

