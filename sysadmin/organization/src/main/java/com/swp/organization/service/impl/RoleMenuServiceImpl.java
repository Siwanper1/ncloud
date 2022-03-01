package com.swp.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.RoleMenuMapper;
import com.swp.organization.entity.po.RoleMenu;
import com.swp.organization.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public boolean saveBatch(long roleId, Set<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds))
            return false;
        this.removeByRoleId(roleId);
        List<RoleMenu> roleMenus = menuIds.stream().map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList());
        return this.saveBatch(roleMenus);
    }

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
        List<RoleMenu> list = this.list(queryWrapper);
        return list.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
    }

    @Override
    public Set<Long> queryByRoleIds(Set<Long> roleIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("role_id", roleIds);
        List<RoleMenu> list = this.list(queryWrapper);
        return list.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
    }


}
