package com.swp.auth.authentication.client.service.impl;

import com.swp.auth.authentication.client.provider.AuthenticationProvider;
import com.swp.auth.authentication.client.service.AuthService;
import com.swp.ncloud.common.core.entity.vo.Result;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    private static final String BEARER = "Bearer ";
    /**
     * 认证可以忽略的url，以逗号分隔
     */
    @Value("${gateway.ignore.authentication.startWith}")
    private String ignoreUrl = "/oauth";

    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public Result auth(String authorization, String url, String method) {
        log.info("url:{}, method:{}, authorization:{}", url, method, authorization);
        Result result = authenticationProvider.auth(authorization, url, method);
        return result;
    }

    @Override
    public boolean ignoreAuthenticationUrl(String url) {
        return Arrays.stream(this.ignoreUrl.split(",")).anyMatch(ignoreUrl -> url.startsWith(ignoreUrl.trim()));
    }

    @Override
    public boolean hasPermission(Result result) {
        return result.isSuccess() && (boolean) result.getData();
    }

    @Override
    public boolean hasPermission(String authorization, String url, String method) {
        // 如果请求未携带token信息，直接返回false
        if (StringUtils.isBlank(authorization) || !authorization.startsWith(BEARER))
        {
            log.info("token 为空 或者 不以BEARER 为首");
            return false;
        }
        // 判断token的格式是否正确
        if (invalidAuthorization(authorization)) {
            log.info("token 格式不正确");
            return false;
        }
        // 远程调用认证服务
        return hasPermission(auth(authorization, url, method));
    }

    @Override
    public Jws<Claims> jwtToken(String token) {
        if (token.startsWith(BEARER)) {
            token = StringUtils.substring(token, BEARER.length());
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(token);
        return claimsJws;
    }

    @Override
    public boolean invalidAuthorization(String authorization) {
        boolean result = Boolean.TRUE;
        try {
            jwtToken(authorization);
            result = Boolean.FALSE;
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            log.error("user token error :{}", ex.getMessage());
        }
        return result;
    }

    @Override
    public void parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(token);
        log.info("token : {}", claimsJws);
    }


}
