package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.ncloud.common.core.exception.BaseException;
import com.swp.organization.dao.PositionMapper;
import com.swp.organization.entity.param.PositionQueryParam;
import com.swp.organization.entity.po.Position;
import com.swp.organization.exception.OrganizationErrorType;
import com.swp.organization.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    @Override
    public boolean add(Position position) {
        return this.save(position);
    }

    @CacheInvalidate(name = "position::", key = "#id")
    @Override
    public boolean delete(long id) {
        return this.removeById(id);
    }

    @CacheInvalidate(name = "position::", key = "#position.id")
    @Override
    public boolean update(Position position) {
        return this.updateById(position);
    }

    @Cached(name = "position::", key = "#id", cacheType = CacheType.BOTH)
    @Override
    public Position get(long id) {
        Position position = this.getById(id);
        if (position == null) {
            throw new BaseException(OrganizationErrorType.POSITION_NOT_FOUND);
        }
        return position;
    }

    @Override
    public IPage<Position> query(Page page, PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = positionQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(positionQueryParam.getName()),"name", positionQueryParam.getName());
        IPage positions = this.page(page, queryWrapper);
        return positions;
    }
}
