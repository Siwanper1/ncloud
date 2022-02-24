package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.RoleQueryParam;
import com.swp.organization.entity.po.Role;

import java.util.List;

public interface RoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean add(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    boolean update(Role role);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role get(int id);

    /**
     * 查询所有的角色
     * @return
     */
    List<Role> getAll();

    /**
     * 分页查询
     * @param page
     * @param roleQueryParam
     * @return
     */
    IPage<Role> query(Page<Role> page, RoleQueryParam roleQueryParam);

}
