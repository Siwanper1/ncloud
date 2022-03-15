package com.siwanper.gateway.web.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swp.auth.authentication.client.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = "com.swp.auth.authentication.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final String X_CLIENT_TOKEN = "x_client_token";
    private static final String X_CLIENT_USER_TOKEN  = "x_client_user_token";

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取token
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        // 获取method 和 url
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.info("url:{}, method:{}, token:{}", url, method, authorization);
        // 判断是否为忽略的访问路径，如果是直接跳过过滤器
        if (authService.ignoreAuthenticationUrl(url)) {
            log.info("ignore url : {}", url);
            return chain.filter(exchange);
        }
        // 判断是否有访问权限
        if (authService.hasPermission(authorization, url, method)) {
            ServerHttpRequest.Builder builder = request.mutate();
            builder.header(X_CLIENT_TOKEN, authorization);
            builder.header(X_CLIENT_USER_TOKEN, getUserToken(authorization));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        // 如果没有访问权限，返回无权限
        return unauthorized(exchange);
    }

    /**
     * 将jwt token 解析为json格式
     * @param authorization
     * @return
     */
    private String getUserToken(String authorization){
        ObjectMapper mapper = new ObjectMapper();
        String token = null;
        try {
            token = mapper.writer().writeValueAsString(authService.jwtToken(authorization).getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("token 解析错误");
        }
        return token;
    }

    //无权限访问
    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return exchange.getResponse().writeWith(Flux.just(wrap));
    }

}
