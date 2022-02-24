package com.swp.organization.service;

import java.util.Set;

public interface UserRoleService {

    /**
     * 用户批量关联角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveBatch(long userId, Set<Long> roleIds);

    /**
     * 查询用户的所有角色
     * @param useId
     * @return
     */
    Set<Long> queryByUserId(long useId);

    /**
     * 删除用户的角色
     * @param userId
     * @return
     */
    boolean removeByUserId(long userId);

}
