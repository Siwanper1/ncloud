package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.MenuQueryParam;
import com.swp.organization.entity.po.Menu;

public interface MenuService {

    boolean add(Menu menu);

    boolean delete(long id);

    boolean update(Menu menu);

    Menu get(long id);

    IPage<Menu> query(Page page, MenuQueryParam queryParam);

}
