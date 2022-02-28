package com.swp.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.ncloud.common.core.exception.BaseException;
import com.swp.organization.dao.MenuMapper;
import com.swp.organization.entity.param.MenuQueryParam;
import com.swp.organization.entity.po.Menu;
import com.swp.organization.entity.po.Resource;
import com.swp.organization.exception.OrganizationErrorType;
import com.swp.organization.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public boolean add(Menu menu) {
        boolean result = this.save(menu);
        log.info("add menu : {}", result);
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = this.removeById(id);
        log.info("remove menu: {}", result);
        return result;
    }

    @Override
    public boolean update(Menu menu) {
        boolean result = this.updateById(menu);
        log.info("update menu: {}", result);
        return result;
    }

    @Override
    public Menu get(long id) {
        Menu menu = this.getById(id);
        if (menu == null) {
            throw new BaseException(OrganizationErrorType.MENU_NOT_FOUND);
        }
        return menu;
    }

    @Override
    public IPage<Menu> query(Page page, MenuQueryParam queryParam) {
        QueryWrapper<Menu> queryWrapper = queryParam.build();
        queryWrapper.like(StringUtils.isNotBlank(queryParam.getName()), "name", queryParam.getName());
        IPage menus = this.page(page, queryWrapper);
        return menus;
    }
}
