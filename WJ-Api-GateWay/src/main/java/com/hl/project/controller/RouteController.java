package com.hl.project.controller;


import com.hl.project.configuration.GatewayServiceHandler;
import com.hl.project.dto.GatewayRouteDto;
import com.hl.project.entity.GatewayRoute;
import com.hl.project.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *  1.直接在数据库添加路由配置信息，手动刷新，使配置信息立即生效；
 *
 *  2.前端页面增、删、改路由配置信息，并使配置信息立即生效；
 *
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private GatewayServiceHandler gatewayServiceHandler;

    @Autowired
    private GatewayRouteService gatewayRouteService;

    /**
     * 刷新路由配置
     * @return
     */
    @GetMapping("/refresh")
    public String refresh() throws Exception {
        return this.gatewayServiceHandler.loadRouteConfig();
    }

    /**
     * 增加路由记录
     *
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDto gatewayRouteDto) throws Exception {
        gatewayRouteService.add(gatewayRouteDto);
        gatewayServiceHandler.loadRouteConfig();
        return "success";
    }

    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDto gatewayRouteDto) throws Exception {
        gatewayRouteService.update(gatewayRouteDto);
        gatewayServiceHandler.loadRouteConfig();
        return "success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws Exception {
        gatewayRouteService.delete(id);
        gatewayServiceHandler.deleteRoute(id);
        return "success";
    }

    @GetMapping("/routes")
    public List<GatewayRoute> routes() throws Exception {
        return gatewayRouteService.queryAllRoutes();
    }

}