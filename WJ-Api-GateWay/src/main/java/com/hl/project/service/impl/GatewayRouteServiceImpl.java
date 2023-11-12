package com.hl.project.service.impl;


import com.hl.project.configuration.GatewayServiceHandler;
import com.hl.project.dao.GatewayRouteMapper;
import com.hl.project.model.dto.GatewayRouteDto;
import com.hl.project.model.entity.GatewayRoute;
import com.hl.project.service.InnerGatewayRouteService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  自定义service层，增、删、改、查数据库路由配置信息
 */
@Service(version = "${dubbo.service.version}")
@org.springframework.stereotype.Service
public class GatewayRouteServiceImpl implements InnerGatewayRouteService {

    @Resource
    private GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    private GatewayServiceHandler gatewayServiceHandler;

    @Override
    public String refresh()  {
        return gatewayServiceHandler.loadRouteConfig();
    }

    @Override
    public void deleteRoute(String routeId) {
        gatewayServiceHandler.deleteRoute(routeId);
    }

    @Override
    public Integer add(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayRoute.setCreateDate(new Date());
        gatewayRoute.setCreatorId("");
        if (gatewayRoute.getId()!=null){
            return gatewayRouteMapper.updateByPrimaryKeySelective(gatewayRoute);
        }
        return gatewayRouteMapper.insertSelective(gatewayRoute);
    }

    @Override
    public Integer update(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayRoute.setUpdateDate(new Date());
        gatewayRoute.setUpdateId("");
        return gatewayRouteMapper.updateByServiceId(gatewayRoute);
    }

    @Override
    public Integer delete(String id) {
        return gatewayRouteMapper.deleteByServiceId(Long.parseLong(id));
    }

    @Override
    public List<GatewayRoute> queryAllRoutes(){
        return gatewayRouteMapper.queryAllRoutes();
    }


}