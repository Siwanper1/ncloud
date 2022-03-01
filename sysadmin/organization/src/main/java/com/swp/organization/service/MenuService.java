package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.MenuQueryParam;
import com.swp.organization.entity.po.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    boolean add(Menu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    boolean update(Menu menu);

    /**
     * 根据ID查询菜单
     * @param id
     * @return
     */
    Menu get(long id);

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAll();

    /**
     * 分页查询菜单
     * @param page
     * @param queryParam
     * @return
     */
    IPage<Menu> query(Page page, MenuQueryParam queryParam);

}
