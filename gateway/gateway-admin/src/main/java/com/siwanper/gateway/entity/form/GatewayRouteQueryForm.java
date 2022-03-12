package com.siwanper.gateway.entity.form;

import com.siwanper.gateway.entity.param.GatewayRouteQueryParam;
import com.swp.ncloud.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "查找网关")
@Data
public class GatewayRouteQueryForm extends BaseQueryForm<GatewayRouteQueryParam> {

    @ApiModelProperty("查询的uri")
    private String uri;

    @ApiModelProperty("开始查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeStart;
    @ApiModelProperty("结束查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeEnd;

}
