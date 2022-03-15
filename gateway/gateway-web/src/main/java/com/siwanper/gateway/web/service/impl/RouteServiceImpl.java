package com.siwanper.gateway.web.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.siwanper.gateway.web.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RouteServiceImpl implements RouteService {

    private static final String gatewayRouteCacheName = "GATEWAYROUT::";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @CreateCache(name = gatewayRouteCacheName, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayCache;

    private Map<String, RouteDefinition> routeDefinitionMap = new HashMap<>();

    @PostConstruct
    private void loadRouteDefinitions(){
        Set<String> keys = stringRedisTemplate.keys(gatewayRouteCacheName + "*");
        if (CollectionUtils.isEmpty(keys))
            return;
        Set<String> gatewayKeys = keys.stream().map(key -> {
            return key.replace(gatewayRouteCacheName, StringUtils.EMPTY);
        }).collect(Collectors.toSet());
        log.info("预加载的路由：{}" ,gatewayKeys);
        Map<String, RouteDefinition> allRoutes = gatewayCache.getAll(gatewayKeys);
        //"The parameter [lb://product:9002] format is incorrect, scheme can not be empty
        // redis 获取的uri 格式错误，需要重新初始化
        allRoutes.values().forEach(routeDefinition -> {
            try {
                routeDefinition.setUri(new URI(routeDefinition.getUri().toASCIIString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                log.error("网关加载RouteDefinition异常：", e);
            }
        });
        log.info("全部路由：{}" ,allRoutes);
        routeDefinitionMap.putAll(allRoutes);
        log.info("共初始化路由信息格数：{}", routeDefinitionMap.size());
        log.info("共初使化路由信息：{}", routeDefinitionMap);
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMap.values();
    }

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        routeDefinitionMap.put(routeDefinition.getId(), routeDefinition);
        log.info("新增路由：{}, 目前包括路由格数:{}", routeDefinition, routeDefinitionMap.size());
        return true;
    }

    @Override
    public boolean delete(String id) {
        routeDefinitionMap.remove(id);
        log.info("删除路由, 目前包括路由格数:{}", routeDefinitionMap.size());
        return true;
    }


}
