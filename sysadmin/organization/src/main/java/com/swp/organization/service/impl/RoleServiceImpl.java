package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.RoleMapper;
import com.swp.organization.entity.param.RoleQueryParam;
import com.swp.organization.entity.po.Role;
import com.swp.organization.exception.RoleNotFoundException;
import com.swp.organization.service.RoleResourceService;
import com.swp.organization.service.RoleService;
import com.swp.organization.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;
    //@CreateCache

    @Transactional
    @Override
    public boolean add(Role role) {
        boolean save = this.save(role);
        log.info("add role : {}", save);
        this.roleResourceService.saveBatch(role.getId(), role.getResourceIds());
        return save;
    }
    @Transactional
    @CacheInvalidate(name = "role::", key = "#id")
    @Override
    public boolean delete(int id) {
        boolean result = this.removeById(id);
        log.info("remove role : {}", result);
        this.roleResourceService.removeByRoleId(id);
        return result;
    }
    @Transactional
    @CacheInvalidate(name = "role::", key = "#role.id")
    @Override
    public boolean update(Role role) {
        boolean result = this.updateById(role);
        this.roleResourceService.saveBatch(role.getId(), role.getResourceIds());
        return result;
    }

    @Cached(name = "role::", key = "#id", cacheType = CacheType.BOTH)
    @Override
    public Role get(int id) {
        Role role = this.getById(id);
        if (role == null) {
            throw new RoleNotFoundException("角色不存在");
        }
        role.setResourceIds(this.roleResourceService.queryByRoleId(id));
        return role;
    }

    @Cached(name = "role4user::", key = "#userId", cacheType = CacheType.BOTH)
    @Override
    public List<Role> getByUserId(long userId) {
        Set<Long> roleIds = this.userRoleService.queryByUserId(userId);
        return (List<Role>) this.listByIds(roleIds);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = this.list();
        roles.stream().forEach(role -> role.setResourceIds(this.roleResourceService.queryByRoleId(role.getId())));
        return roles;
    }

    @Override
    public IPage<Role> query(Page<Role> page, RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> wrapper = roleQueryParam.build();
        wrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode());
        wrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName());
        IPage<Role> roles = this.page(page, wrapper);
        return roles;
    }
}
