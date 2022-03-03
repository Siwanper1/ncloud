package com.swp.auth.authorizationserver.service.impl;

import com.swp.auth.authorizationserver.entity.Role;
import com.swp.auth.authorizationserver.provider.OrganizationProvider;
import com.swp.auth.authorizationserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryRolesByUserId(String userId) {
        return organizationProvider.getByUserId(Long.valueOf(userId)).getData();
    }
}
