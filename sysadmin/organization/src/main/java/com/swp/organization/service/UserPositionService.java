package com.swp.organization.service;

import java.util.Set;

public interface UserPositionService {

    /**
     * 批量添加用户职位
     * @param userId
     * @param positionIds
     * @return
     */
    boolean saveBatch(long userId, Set<Long> positionIds);

    /**
     * 根据用户Id删除职位
     * @param userId
     * @return
     */
    boolean deleteByUserId(long userId);

    /**
     * 根据用户Id获取职位Id
     * @param userId
     * @return
     */
    Set<Long> getByUserId(long userId);

}
