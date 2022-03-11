package com.siwanper.gateway.controller;

import com.siwanper.gateway.service.GatewayRouteService;
import com.swp.ncloud.common.core.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayRouteController {

    @Autowired
    private GatewayRouteService gatewayRouteService;

    @GetMapping("/{id}")
    public Result get(@PathVariable int id){
        return Result.success(this.gatewayRouteService.get(id));
    }

}
