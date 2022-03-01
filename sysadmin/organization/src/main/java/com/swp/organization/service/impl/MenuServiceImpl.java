package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Transactional
    @Override
    public boolean add(Menu menu) {
        boolean result = this.save(menu);
        log.info("add menu : {}", result);
        return result;
    }

    @Transactional
    @CacheInvalidate(name = "menu::", key = "#id")
    @Override
    public boolean delete(long id) {
        boolean result = this.removeById(id);
        log.info("remove menu: {}", result);
        return result;
    }

    @Transactional
    @CacheInvalidate(name = "menu::", key = "#menu.id")
    @Override
    public boolean update(Menu menu) {
        boolean result = this.updateById(menu);
        log.info("update menu: {}", result);
        return result;
    }

    @Cached(name = "menu::", key = "#id", cacheType = CacheType.BOTH)
    @Override
    public Menu get(long id) {
        Menu menu = this.getById(id);
        if (menu == null) {
            throw new BaseException(OrganizationErrorType.MENU_NOT_FOUND);
        }
        return menu;
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> list = query(-1, this.list());
        return list;
    }

    private List<Menu> query(long parentId, List<Menu> list) {
        List<Menu> child = list.stream().filter(menu -> menu.getParentId() == parentId).collect(Collectors.toList());
        child.stream().forEach(menu -> menu.setChild(query(menu.getId(),list)));
        return child;
    }


    @Override
    public IPage<Menu> query(Page page, MenuQueryParam queryParam) {
        QueryWrapper<Menu> queryWrapper = queryParam.build();
        queryWrapper.like(StringUtils.isNotBlank(queryParam.getName()), "name", queryParam.getName());
        IPage menus = this.page(page, queryWrapper);
        return menus;
    }
}
