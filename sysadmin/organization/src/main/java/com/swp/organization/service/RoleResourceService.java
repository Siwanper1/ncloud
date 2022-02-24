package com.swp.organization.service;

import com.swp.organization.entity.po.RoleResource;

import java.util.List;
import java.util.Set;

public interface RoleResourceService {

    boolean saveBatch(long roleId, Set<Long> resourceIds);

    boolean removeByRoleId(long roleId);

    Set<Long> queryByRoleId(long roleId);

    List<RoleResource> queryByRoleIds(Set<Long> roleIds);

}
