package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.ncloud.common.core.exception.BaseException;
import com.swp.ncloud.common.core.exception.SystemErrorType;
import com.swp.organization.dao.ResourceMapper;
import com.swp.organization.entity.param.ResourceQueryParam;
import com.swp.organization.entity.po.Resource;
import com.swp.organization.exception.OrganizationErrorType;
import com.swp.organization.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Transactional
    @Override
    public boolean add(Resource resource) {
        boolean result = this.save(resource);
        log.info("add resource : {}", result);
        return result;
    }

    @Transactional
    @CacheInvalidate(name = "resource::", key = "#id")
    @Override
    public boolean delete(int id) {
        boolean result = this.removeById(id);
        log.info("remove resource: {}", result);
        return result;
    }

    @Transactional
    @CacheInvalidate(name = "resource::", key = "#resource.id")
    @Override
    public boolean update(Resource resource) {
        boolean result = this.updateById(resource);
        log.info("udpate resource :{}", result);
        return result;
    }

    @Cached(name = "resource::", key = "#id", cacheType = CacheType.BOTH)
    @Override
    public Resource get(int id) {
        Resource resource = this.getById(id);
        if (resource == null) {
            throw new BaseException(OrganizationErrorType.RESOURCE_NOT_FOUND);
        }
        return resource;
    }

    @Override
    public List<Resource> getAll() {
        return this.list();
    }

    @Override
    public IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam) {
        QueryWrapper<Resource> wrapper = resourceQueryParam.build();
        wrapper.like(StringUtils.isNotBlank(resourceQueryParam.getName()), "name", resourceQueryParam.getName());
        IPage resources = this.page(page, wrapper);
        return resources;
    }
}
