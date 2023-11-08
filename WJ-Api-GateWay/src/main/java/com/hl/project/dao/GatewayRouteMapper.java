package com.hl.project.dao;


import com.hl.project.entity.GatewayRoute;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GatewayRoute record);

    int insertSelective(GatewayRoute record);

    GatewayRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GatewayRoute record);

    int updateByPrimaryKey(GatewayRoute record);

    List<GatewayRoute> queryAllRoutes();
}