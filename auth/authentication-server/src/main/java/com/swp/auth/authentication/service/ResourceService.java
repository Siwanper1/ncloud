package com.swp.auth.authentication.service;

import com.swp.organization.entity.po.Resource;
import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface ResourceService {

    /**
     * 加载所有资源
     */
    void loadResource();

    /**
     * 根据请求的url和方法，获取资源
     * @param request
     * @return
     */
    ConfigAttribute findConfigAttributeByRequest(HttpServletRequest request);

    /**
     * 根据用户名获取资源
     * @param username
     * @return
     */
    Set<Resource> queryResourceByUser(String username);

    /**
     * 保存资源到内存中
     * @param resource
     */
    void save(Resource resource);

    /**
     * 从内存中删除资源
     * @param resource
     */
    void remove(Resource resource);

}
