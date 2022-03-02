package com.swp.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.UserPositionMapper;
import com.swp.organization.entity.po.UserPosition;
import com.swp.organization.service.UserPositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserPositionServiceImpl extends ServiceImpl<UserPositionMapper, UserPosition> implements UserPositionService {
    @Override
    public boolean saveBatch(long userId, Set<Long> positionIds) {
        if (CollectionUtils.isEmpty(positionIds))
            return false;
        this.deleteByUserId(userId);
        List<UserPosition> userPositions = positionIds.stream().map(positionId -> new UserPosition(userId, positionId)).collect(Collectors.toList());
        return this.saveBatch(userPositions);
    }

    @Override
    public boolean deleteByUserId(long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return  this.remove(queryWrapper);
    }

    @Override
    public Set<Long> getByUserId(long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        List<UserPosition> list = this.list(queryWrapper);
        return list.stream().map(UserPosition::getPositionId).collect(Collectors.toSet());
    }
}
