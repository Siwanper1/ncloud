package com.swp.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swp.ncloud.common.core.exception.BaseException;
import com.swp.organization.dao.GroupMapper;
import com.swp.organization.entity.param.GroupQueryParam;
import com.swp.organization.entity.po.Group;
import com.swp.organization.exception.OrganizationErrorType;
import com.swp.organization.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Transactional
    @Override
    public boolean add(Group group) {
        return this.save(group);
    }

    @Transactional
    @CacheInvalidate(name = "group::", key = "#id")
    @Override
    public boolean delete(long id) {
        return this.removeById(id);
    }

    @Transactional
    @CacheInvalidate(name = "group::", key = "#group.id")
    @Override
    public boolean update(Group group) {
        return this.updateById(group);
    }

    @Cached(name = "group::", key = "#id")
    @Override
    public Group get(long id) {
        Group group = this.getById(id);
        if (group == null)
            throw new BaseException(OrganizationErrorType.GROUP_NOT_FOUND);
        return group;
    }

    @Override
    public List<Group> getAll(){
        List<Group> list = queryByParentId(-1, this.list());
        return list;
    }

    private List<Group> queryByParentId(long parentId, List<Group> list){
        List<Group> child = list.stream().filter(group -> group.getParentId() == parentId).collect(Collectors.toList());
        child.stream().forEach(group -> group.setChild(queryByParentId(group.getId(), list)));
        return child;
    }

    @Override
    public IPage<Group> query(Page page, GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = groupQueryParam.build();
        queryWrapper.eq(StringUtils.isNotBlank(groupQueryParam.getName()),"name", groupQueryParam.getName());
        return this.page(page, queryWrapper);
    }

}
