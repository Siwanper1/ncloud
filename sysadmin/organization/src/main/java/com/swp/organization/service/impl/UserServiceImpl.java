package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.organization.dao.UserMapper;
import com.swp.organization.entity.param.UserQueryParam;
import com.swp.organization.entity.po.User;
import com.swp.organization.entity.vo.UserVo;
import com.swp.organization.exception.UserNotFoundException;
import com.swp.organization.service.UserRoleService;
import com.swp.organization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Cached(name = "user::", key = "#id", cacheType = CacheType.BOTH)
    public UserVo get(String id) {
        User user = this.getById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return new UserVo(user);
    }

    @Cached(name = "user::", key = "#uniqueId", cacheType = CacheType.BOTH)
    @Override
    public User getByUniqueId(String uniqueId) {
        User user = this.getOne(new QueryWrapper<User>().eq("username", uniqueId).or().eq("mobile", uniqueId));
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException("用户不存在");
        }
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return user;
    }

    @Transactional
    @Override
    public boolean add(User user) {
        if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        boolean save = this.save(user);
        log.info("add result : {}", save, user.getId());
        this.userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return save;
    }

    @Transactional
    @CacheInvalidate(name = "user::" ,key = "#id")
    @Override
    public boolean delete(long id) {
        User user = this.getById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException("用户不存在");
        } else {
            this.userRoleService.removeByUserId(id);
            return this.removeById(id);
        }
    }

    @Transactional
    @CacheInvalidate(name = "user::" ,key = "#user.id")
    @Override
    public boolean update(User user) {
        if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        this.userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return this.updateById(user);
    }

    @Override
    public IPage<UserVo> query(Page<User> page, UserQueryParam queryParam) {
        QueryWrapper<User> queryWrapper = queryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(queryParam.getUsername()), "username", queryParam.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(queryParam.getMobile()), "mobile", queryParam.getMobile());
        queryWrapper.eq(StringUtils.isNotBlank(queryParam.getName()), "name", queryParam.getName());
        IPage<User> users = this.page(page, queryWrapper);
        return users.convert(UserVo::new);
    }
}
