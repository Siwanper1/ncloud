package com.siwanper.gateway.service;

import com.siwanper.gateway.entity.param.GatewayRouteQueryParam;
import com.siwanper.gateway.entity.po.GatewayRoute;
import com.siwanper.gateway.entity.vo.GatewayRouteVo;

import java.util.List;

public interface GatewayRouteService {

    /**
     * 添加网关
     * @param gatewayRoute
     * @return
     */
    boolean add(GatewayRoute gatewayRoute);

    /**
     * 删除网关
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 修改网关
     * @param gatewayRoute
     * @return
     */
    boolean update(GatewayRoute gatewayRoute);
    /**
     * 根据ID获取网关
     * @param id
     * @return
     */
    GatewayRoute get(int id);

    /**
     * 查询网关
     * @param gatewayRouteQueryParam
     * @return
     */
    List<GatewayRouteVo> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    /**
     * 重新加载网关到redis中
     */
    boolean overload();

}
