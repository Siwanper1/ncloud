package com.swp.auth.authentication.controller;

import com.swp.auth.authentication.service.AuthenticationService;
import com.swp.auth.authentication.service.HttpServletRequestAuthWrapper;
import com.swp.ncloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 根据用户的token ,访问url和method 判断用户是否有权限访问
     * @param url
     * @param method
     * @param request
     * @return
     */
    @PostMapping("/auth/permission")
    public Result decide(@RequestParam  String url, @RequestParam String method, HttpServletRequest request){
        log.info("url : {}, method : {}", url, method);
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Result.success(decide);
    }

}
