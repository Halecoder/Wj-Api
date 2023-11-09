package com.hl.project.service;

import com.hl.project.model.dto.GatewayRouteDto;
import com.hl.project.model.entity.GatewayRoute;

import java.util.List;

public interface InnerGatewayRouteService {
    String refresh();

    Integer add(GatewayRouteDto gatewayRouteDto);

    Integer update(GatewayRouteDto gatewayRouteDto);

    Integer delete(String id);

    List<GatewayRoute> queryAllRoutes();
}
