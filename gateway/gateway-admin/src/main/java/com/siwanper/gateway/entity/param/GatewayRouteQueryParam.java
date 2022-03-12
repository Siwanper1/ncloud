package com.siwanper.gateway.entity.param;

import com.siwanper.gateway.entity.po.GatewayRoute;
import com.swp.ncloud.common.web.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam<GatewayRoute> {

    private String uri;

}
