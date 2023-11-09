package com.hl.project.dao;


import com.hl.project.model.entity.GatewayRoute;

import java.util.List;

public interface GatewayRouteMapper {
    int deleteByServiceId(Long id);

    int insert(GatewayRoute record);

    int insertSelective(GatewayRoute record);

    GatewayRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GatewayRoute record);

    int updateByPrimaryKey(GatewayRoute record);

    List<GatewayRoute> queryAllRoutes();
}