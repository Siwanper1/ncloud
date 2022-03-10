package com.swp.auth.authentication.service.impl;

import com.swp.auth.authentication.entity.po.Resource;
import com.swp.auth.authentication.provider.ResourceProvider;
import com.swp.auth.authentication.service.NewMvcRequestMatcher;
import com.swp.auth.authentication.service.ResourceService;
import com.swp.ncloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private HandlerMappingIntrospector handlerMappingIntrospector;

    @Autowired
    private ResourceProvider resourceProvider;

    private static final Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap<>();
    private static final String NONEXSIT_URL = "NONEXSIT_URL";
    @Override
    public void loadResource() {
        Result<Set<Resource>> result = resourceProvider.resources();
        if (result.isFail()) {
            System.exit(11);
        }
        Set<Resource> resources = result.getData();
        Map<NewMvcRequestMatcher, SecurityConfig> allResourceConfigAttributes = resources.stream().collect(Collectors.toMap(
                resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                resource -> new SecurityConfig(resource.getCode())
        ));
        resourceConfigAttributes.putAll(allResourceConfigAttributes);
        log.info("all resource : {}", resourceConfigAttributes);
    }

    @Override
    public ConfigAttribute findConfigAttributeByRequest(HttpServletRequest request) {
        ConfigAttribute attribute = resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(request))
                .map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
                .peek(configAttribute -> log.info("url在资源池重的配置：{}", configAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig(NONEXSIT_URL));
        return attribute;
    }

    @Override
    public Set<Resource> queryResourceByUser(String username) {
        Result<Set<Resource>> result = resourceProvider.resources(username);
        return result.getData();
    }

    private NewMvcRequestMatcher newMvcRequestMatcher(String pattern, String method) {
        return new NewMvcRequestMatcher(handlerMappingIntrospector, pattern, method);
    }
}
