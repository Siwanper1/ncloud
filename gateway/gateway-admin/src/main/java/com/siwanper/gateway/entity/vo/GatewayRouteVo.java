package com.siwanper.gateway.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siwanper.gateway.entity.po.GatewayRoute;
import lombok.Data;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Data
public class GatewayRouteVo {

    private long id;
    private String routeId;
    private String uri;
    private List<PredicateDefinition> predicates;
    private List<RouteDefinition> filters;
    private String description;
    private String status;
    private String deleted;
    private Integer orders;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;

    public GatewayRouteVo(GatewayRoute gatewayRoute){
        this.id = gatewayRoute.getId();
        this.routeId = gatewayRoute.getRouteId();
        this.uri = gatewayRoute.getUri();
        this.description = gatewayRoute.getDescription();
        this.status = gatewayRoute.getStatus();
        this.deleted = gatewayRoute.getDeleted();
        this.orders = gatewayRoute.getOrders();
        this.createdBy = gatewayRoute.getCreatedBy();
        this.updatedBy = gatewayRoute.getUpdatedBy();
        this.createdTime = gatewayRoute.getCreatedTime();
        this.updatedBy = gatewayRoute.getUpdatedBy();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>(){});
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
