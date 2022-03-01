package com.swp.organization.service;

import com.swp.organization.entity.po.RoleMenu;

import java.util.List;
import java.util.Set;

public interface RoleMenuService {

    /**
     * 批量添加菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean saveBatch(long roleId, Set<Long> menuIds);

    /**
     * 根据角色Id删除
     * @param roleId
     * @return
     */
    boolean removeByRoleId(long roleId);

    /**
     * 根据角色ID查询
     * @param roleId
     * @return
     */
    Set<Long> queryByRoleId(long roleId);

    /**
     * 多角色菜单查询
     * @param roleIds
     * @return
     */
    Set<Long> queryByRoleIds(Set<Long> roleIds);

}
