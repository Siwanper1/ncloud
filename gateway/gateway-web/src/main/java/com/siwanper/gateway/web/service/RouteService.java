package com.siwanper.gateway.web.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

public interface RouteService {

    /**
     * 从redis中加载所有的网关
     * @return
     */
    Collection<RouteDefinition> getRouteDefinitions();

    /**
     * 新增路由
     * @param routeDefinition
     * @return
     */
    boolean save(RouteDefinition routeDefinition);

    /**
     * 删除路由
     * @param id
     * @return
     */
    boolean delete(String id);

}
