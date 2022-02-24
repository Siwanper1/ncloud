package com.swp.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.UserRoleMapper;
import com.swp.organization.entity.po.UserRole;
import com.swp.organization.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Transactional
    @Override
    public boolean saveBatch(long userId, Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }
        removeByUserId(userId);
        Set<UserRole> userRoles = roleIds.stream().map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toSet());
        boolean result = this.saveBatch(userRoles);
        log.info("save userRole : {}", result);
        return result;
    }

    @Override
    public Set<Long> queryByUserId(long useId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", useId);
        List<UserRole> userRoles = this.list(queryWrapper);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public boolean removeByUserId(long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return this.remove(queryWrapper);
    }
}
