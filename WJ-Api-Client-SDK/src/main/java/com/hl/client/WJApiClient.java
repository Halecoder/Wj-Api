package com.hl.client;



import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hl.model.InvokeInterfaceRequest;
import com.hl.model.User;
import com.hl.utils.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.hl.utils.SignUtils.genSign;

public class WJApiClient {

    //走网关 进行相关处理，比如鉴权、限流、熔断、负载均衡、API调用统计等 网关处理后再转发回来进行业务处理


    private String gatewayHost;

    private String accessKey;

    private String secretKey;

    public WJApiClient(String accessKey, String secretKey,String gatewayHost) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.gatewayHost = gatewayHost;
    }

    private Map<String, String> getHeaders(String body) throws UnsupportedEncodingException {
        Map<String, String> header = new HashMap<>();
        header.put("accessKey", accessKey);
        header.put("sign", SignUtils.genSign(body, secretKey));
        // 防止中文乱码
        header.put("body", URLEncoder.encode(body, StandardCharsets.UTF_8.name()));
        header.put("nonce", RandomUtil.randomNumbers(5));
        header.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return header;
    }

    public String getNameByPostWithJson(User user) throws UnsupportedEncodingException {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(gatewayHost+"/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println("response = " + response);
        System.out.println("status = " + response.getStatus());
        if (response.isOk()) {
            return response.body();
        }
        return "fail";
    }


    public String postResultByInvoke(InvokeInterfaceRequest invokeInterfaceRequest,String method,String uri) throws UnsupportedEncodingException {
        String json = JSONUtil.toJsonStr(invokeInterfaceRequest);
        String id = String.valueOf(invokeInterfaceRequest.getId()); //id用来区分不同的服务
        HttpResponse response = null;
        if (StrUtil.equals(method, "POST",true)){
            response = HttpRequest.post(gatewayHost + '/'+ id + uri)
                    .addHeaders(getHeaders(json))
                    .body(json)
                    .execute();
        }else{
            response = HttpRequest.get(gatewayHost +'/'+ id + uri)
                    .addHeaders(getHeaders(json))
                    .body(json)
                    .execute();
        }

        System.out.println("response = " + response);
        System.out.println("status = " + response.getStatus());
//        if (response.isOk()) {
            return response.body();
//        }
//        return "fail";
    }
}
