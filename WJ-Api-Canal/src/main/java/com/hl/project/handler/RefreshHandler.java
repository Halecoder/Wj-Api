package com.hl.project.handler;

import com.hl.project.model.entity.GatewayRoute;
import com.hl.project.service.InnerGatewayRouteService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "gateway_route")
public class RefreshHandler implements EntryHandler<GatewayRoute> {


    @Reference(version = "${dubbo.service.version}", check = false)
    private InnerGatewayRouteService innerGatewayRouteService;

    /***
     * 增加数据
     */
    @Override
    public void insert(GatewayRoute gatewayRoute){
        System.out.println("执行插入路由信息：");
        innerGatewayRouteService.refresh(); // 刷新路由
    }

    /***
     * 修改数据
     */
    @Override
    public void update(GatewayRoute before, GatewayRoute after){
        System.out.println("执行了更新路由信息：" );
        innerGatewayRouteService.refresh(); // 刷新路由

    }

    /***
     * 删除数据
     */
    @Override
    public void delete(GatewayRoute gatewayRoute){
        System.out.println("执行了删除路由信息：" );
        innerGatewayRouteService.refresh(); // 刷新路由
    }
}
