package com.swp.organization.event;

import com.swp.organization.config.BusConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private void init(){
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routeKey, Object object){
        log.info("send routeKey : {} ,object : {}", routeKey, object);
        rabbitTemplate.convertAndSend(BusConfig.EXCHANGE_NAME, routeKey, object);
    }

}
