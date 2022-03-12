package com.siwanper.gateway.entity.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siwanper.gateway.entity.po.GatewayRoute;
import com.swp.ncloud.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加/修改网关")
public class GatewayRouteForm extends BaseForm<GatewayRoute> {

    @ApiModelProperty(value = "网关ID")
    @NotBlank(message = "网关ID不能为空")
    private String routeId;
    @ApiModelProperty(value = "网关url")
    @NotBlank(message = "网关url不能为空")
    private String uri;
    @ApiModelProperty(value = "网关断言")
    @NotEmpty(message = "网关断言不能为空")
    private List<PredicateDefinition> predicates = new ArrayList<>();
    @ApiModelProperty(value = "网关过滤器")
    private List<FilterDefinition> filters = new ArrayList<>();
    @ApiModelProperty(value = "网关过滤器")
    private String description;
    @ApiModelProperty(value = "网关排序")
    private Integer orders = 0;

    @Override
    public GatewayRoute toPo(Class<GatewayRoute> clazz) {
        GatewayRoute gatewayRoute = new GatewayRoute();
        BeanUtils.copyProperties(this,gatewayRoute);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            gatewayRoute.setPredicates(objectMapper.writeValueAsString(this.getPredicates()));
            gatewayRoute.setFilters(objectMapper.writeValueAsString(this.getFilters()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gatewayRoute;
    }
}
