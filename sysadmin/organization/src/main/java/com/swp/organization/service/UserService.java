package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.UserQueryParam;
import com.swp.organization.entity.po.User;
import com.swp.organization.entity.vo.UserVo;

public interface UserService {

    /**
     * 获取用户
     * @param id
     * @return
     */
    UserVo get(String id);

    /**
     * 根据用户的唯一标识查找用户 （手机号 用户名）
     * @param uniqueId
     * @return
     */
    User getByUniqueId(String uniqueId);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 根据用户id修改用户
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 条件查询用户
     * @param page
     * @param queryParam
     * @return
     */
    IPage<UserVo> query(Page<User> page, UserQueryParam queryParam);

}
