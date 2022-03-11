package com.siwanper.gateway.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("gateway_route")
public class GatewayRoute extends BasePo {

    private String routeId;
    private String uri;
    private String predicates;
    private String filters;
    private String description;
    private String status = "Y";
    @TableLogic
    private String deleted = "N";
    private Integer orders = 0;

}
