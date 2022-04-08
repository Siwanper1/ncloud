package com.swp.auth.authentication.service.impl;

import com.swp.organization.entity.po.Resource;
import com.swp.auth.authentication.service.AuthenticationService;
import com.swp.auth.authentication.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String NONEXSIT_URL = "NONEXSIT_URL";

    @Autowired
    private ResourceService resourceService;

    @Override
    public boolean decide(HttpServletRequest request) {
        // 根据请求获取资源
        ConfigAttribute configAttribute = resourceService.findConfigAttributeByRequest(request);
        if (configAttribute.getAttribute().equals(NONEXSIT_URL)) {
            log.debug("url在资源池中未找到资源");
            return false;
        }
        // 获取认证的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("已经通过认证的用户信息: {}", authentication);
        Set<Resource> resources = resourceService.queryResourceByUser(authentication.getName());
        log.info("用户所拥有的资源：{}", resources);
        // 判断用户是否有请求的资源
        boolean match = resources.stream().anyMatch(resource -> resource.getCode().equals(configAttribute.getAttribute()));
        return match;
    }


}
