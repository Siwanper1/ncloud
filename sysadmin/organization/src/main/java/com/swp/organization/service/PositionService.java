package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.PositionQueryParam;
import com.swp.organization.entity.po.Position;

public interface PositionService {

    boolean add(Position position);

    boolean delete(long id);

    boolean update(Position position);

    Position get(long id);

    IPage<Position> query(Page page, PositionQueryParam positionQueryParam);

}
