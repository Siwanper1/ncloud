package com.siwanper.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siwanper.gateway.dao.GatewayRouteMapper;
import com.siwanper.gateway.entity.po.GatewayRoute;
import com.siwanper.gateway.exception.GatewayNotFoundException;
import com.siwanper.gateway.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements GatewayRouteService {

    @Override
    public GatewayRoute get(int id) {
        GatewayRoute gatewayRoute = this.getById(id);
        if (gatewayRoute == null) {
            throw new GatewayNotFoundException();
        }
        return gatewayRoute;
    }

    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute){
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        routeDefinition.setOrder(gatewayRoute.getOrders());
        return routeDefinition;
    }

}
