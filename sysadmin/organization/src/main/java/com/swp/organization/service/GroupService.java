package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.GroupQueryParam;
import com.swp.organization.entity.po.Group;

import java.util.List;

public interface GroupService {

    boolean add(Group group);

    boolean delete(long id);

    boolean update(Group group);

    Group get(long id);

    List<Group> getAll();

    IPage<Group> query(Page page, GroupQueryParam groupQueryParam);

}
