package com.swp.auth.authentication.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    /**
     * 判断用户是否有权限访问请求
     * @param request
     * @return
     */
    boolean decide(HttpServletRequest request);

}
