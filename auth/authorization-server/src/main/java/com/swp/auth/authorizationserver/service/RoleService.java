package com.swp.auth.authorizationserver.service;

import com.swp.auth.authorizationserver.entity.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> queryRolesByUserId(String userId);

}
