package com.hl.project.service;


import com.hl.project.dao.GatewayRouteMapper;
import com.hl.project.dto.GatewayRouteDto;
import com.hl.project.entity.GatewayRoute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  自定义service层，增、删、改、查数据库路由配置信息
 */
@Service
public class GatewayRouteService {

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

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

    public Integer update(GatewayRouteDto gatewayRouteDto) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
        gatewayRoute.setUpdateDate(new Date());
        gatewayRoute.setUpdateId("");
        return gatewayRouteMapper.updateByPrimaryKeySelective(gatewayRoute);
    }

    public Integer delete(String id) {
        return gatewayRouteMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    public List<GatewayRoute> queryAllRoutes(){
        return gatewayRouteMapper.queryAllRoutes();
    }

}