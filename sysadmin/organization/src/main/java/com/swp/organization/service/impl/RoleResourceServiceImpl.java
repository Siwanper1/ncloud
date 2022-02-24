package com.swp.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.RoleResourceMapper;
import com.swp.organization.entity.po.RoleResource;
import com.swp.organization.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Transactional
    @Override
    public boolean saveBatch(long roleId, Set<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds))
            return false;
        this.removeByRoleId(roleId);
        List<RoleResource> roleResources = resourceIds.stream().map(resourceId -> new RoleResource(roleId, resourceId)).collect(Collectors.toList());
        return this.saveBatch(roleResources);
    }

    @Transactional
    @Override
    public boolean removeByRoleId(long roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        return this.remove(queryWrapper);
    }

    @Override
    public Set<Long> queryByRoleId(long roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        List<RoleResource> roleResources = this.list(queryWrapper);
        return roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<Long> roleIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }
}
