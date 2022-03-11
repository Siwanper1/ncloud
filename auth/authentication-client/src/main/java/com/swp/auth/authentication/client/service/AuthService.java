package com.swp.auth.authentication.client.service;

import com.swp.ncloud.common.core.entity.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface AuthService {

    /**
     * 调用Authentication的鉴权服务，判断用户是否有权限
     * @param authorization
     * @param url
     * @param method
     * @return
     */
    Result auth(String authorization, String url, String method);

    /**
     * 判断是否为忽略的url
     * @param url
     * @return
     */
    boolean ignoreAuthenticationUrl(String url);

    /**
     * 判断鉴权返回的结果，true：有权限
     * @param result
     * @return
     */
    boolean hasPermission(Result result);

    /**
     * 调用鉴权服务，判断用户是否有权限
     * @param authorization
     * @param url
     * @param method
     * @return
     */
    boolean hasPermission(String authorization, String url, String method);

    /**
     * 从认证信息中解析 jwtToken
     * @param token
     * @return
     */
    Jws<Claims> jwtToken(String token);

    /**
     * 判断是否为无效的认证信息
     * @param authorization
     * @return true ： 无效， false ： 有效
     */
    boolean invalidAuthorization(String authorization);

    /**
     * 解析JWT token
     * @param token
     */
    void parseToken(String token);

}
