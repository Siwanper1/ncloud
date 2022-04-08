package com.siwanper.gateway.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BusConfig {

    private static final String QUEUE_NAME = "gateway_route";
    public static final String EXCAHGNE_NAME = "com.swp.gateway";
    public static final String ROUTE_KEY = "gateway.route.add";

    @Bean
    public Queue queue(){
        log.info("queue : {}", QUEUE_NAME);
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange(){
        log.info("exchange : {}", EXCAHGNE_NAME);
        return new TopicExchange(EXCAHGNE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        log.info("bind queue : {} to exchange : {}, with routekey : {}", queue, topicExchange, ROUTE_KEY);
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTE_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

}
