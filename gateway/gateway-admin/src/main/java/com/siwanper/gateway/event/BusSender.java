package com.siwanper.gateway.event;

import com.siwanper.gateway.config.BusConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class BusSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter messageConverter;

    @PostConstruct
    public void init(){
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routeKey, RouteDefinition routeDefinition) {
        log.info("routeKey:{} send object : {}", routeKey, routeDefinition);
        rabbitTemplate.convertAndSend(BusConfig.EXCAHGNE_NAME, routeKey, routeDefinition);
    }

}
