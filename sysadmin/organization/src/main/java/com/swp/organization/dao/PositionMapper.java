package com.swp.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.organization.entity.po.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

}
