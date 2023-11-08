package com.hl.project.configuration;

import com.alibaba.fastjson.JSON;

import com.hl.project.dao.GatewayRouteMapper;
import com.hl.project.entity.GatewayRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * 核心配置类，项目初始化加载数据库的路由配置
 *
 */
@Service
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(GatewayServiceHandler.class);

    @Autowired
    private RedisRouteDefinitionRepository routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    //自己的获取数据dao
    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    // springboot启动后执行
    @Override
    public void run(String... args){
        this.loadRouteConfig();
    }

    public String loadRouteConfig() {
        //从数据库拿到路由配置
        List<GatewayRoute> gatewayRouteList = gatewayRouteMapper.queryAllRoutes();

        log.info("网关配置信息：=====>"+ JSON.toJSONString(gatewayRouteList));

        gatewayRouteList.forEach(gatewayRoute -> {
            RouteDefinition definition = new RouteDefinition();

            Map<String, String> predicateParams = new HashMap<>(8);
            PredicateDefinition predicate = new PredicateDefinition();

            FilterDefinition filter = new FilterDefinition();
            Map<String, String> filterParams = new HashMap<>(8);

            URI uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getUri()).build().toUri();

            definition.setId(gatewayRoute.getId().toString());
            // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory
            predicate.setName("Path");
            predicateParams.put("pattern",gatewayRoute.getPredicates());
            predicate.setArgs(predicateParams);

            // 名称是固定的, 路径去前缀(从前面截取一个，实际上就是截取url，后面的部分才是转发的url)
            filter.setName("StripPrefix");
            filterParams.put("_genkey_0", gatewayRoute.getFilters().toString());
            filter.setArgs(filterParams);

            definition.setPredicates(Arrays.asList(predicate));
            definition.setFilters(Arrays.asList(filter));

            definition.setUri(uri);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });

        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }


    public void deleteRoute(String routeId){
        routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}