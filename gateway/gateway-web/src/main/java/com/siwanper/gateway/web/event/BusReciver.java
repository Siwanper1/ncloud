package com.siwanper.gateway.web.event;

import com.siwanper.gateway.web.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusReciver {

    @Autowired
    private RouteService routeService;

    public void handleMessage(RouteDefinition routeDefinition) {
        log.info("recive message : {}", routeDefinition);
        routeService.save(routeDefinition);
    }

}
