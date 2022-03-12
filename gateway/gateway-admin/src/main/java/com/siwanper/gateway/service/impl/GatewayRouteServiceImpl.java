package com.siwanper.gateway.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siwanper.gateway.dao.GatewayRouteMapper;
import com.siwanper.gateway.entity.param.GatewayRouteQueryParam;
import com.siwanper.gateway.entity.po.GatewayRoute;
import com.siwanper.gateway.entity.vo.GatewayRouteVo;
import com.siwanper.gateway.exception.GatewayNotFoundException;
import com.siwanper.gateway.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GatewayRouteServiceImpl extends ServiceImpl<GatewayRouteMapper, GatewayRoute> implements GatewayRouteService {

    private static final String gatewayRouteCacheName = "GATEWAYROUT::";

    @CreateCache(name = gatewayRouteCacheName, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Override
    public boolean add(GatewayRoute gatewayRoute) {
        boolean result = this.save(gatewayRoute);
        // 将网关缓存在redis中
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        return result;
    }

    @Override
    public boolean delete(int id) {
        GatewayRoute gatewayRoute = this.getById(id);
        gatewayRouteCache.remove(gatewayRoute.getRouteId());
        return this.removeById(id);
    }

    @Override
    public boolean update(GatewayRoute gatewayRoute) {
        boolean result = this.updateById(gatewayRoute);
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        return result;
    }

    @Override
    public GatewayRoute get(int id) {
        GatewayRoute gatewayRoute = this.getById(id);
        if (gatewayRoute == null) {
            throw new GatewayNotFoundException();
        }
        return gatewayRoute;
    }

    @Override
    public List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        QueryWrapper<GatewayRoute> queryWrapper = gatewayRouteQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(gatewayRouteQueryParam.getUri()), "uri", gatewayRouteQueryParam.getUri());
        return this.list(queryWrapper).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
    }

    /**
     * 项目启动加载所有的网关
     * @return
     */
    @Override
    @PostConstruct
    public boolean overload() {
        List<GatewayRoute> list = this.list();
        list.stream().forEach(gatewayRoute -> gatewayRouteCache.put(gatewayRoute.getRouteId(), gatewayRouteToRouteDefinition(gatewayRoute)));
        log.info("全部网关初始化完毕");
        return true;
    }

    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute){
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        routeDefinition.setOrder(gatewayRoute.getOrders());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>(){
            }));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>(){
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeDefinition;
    }

}
