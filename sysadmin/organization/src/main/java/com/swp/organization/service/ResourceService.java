package com.swp.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.organization.entity.param.ResourceQueryParam;
import com.swp.organization.entity.po.Resource;

import java.util.List;

public interface ResourceService {

    /**
     * 添加资源
     * @param resource
     * @return
     */
    boolean add(Resource resource);

    /**
     * 删除资源
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 更新资源
     * @param resource
     * @return
     */
    boolean update(Resource resource);

    /**
     * 根据ID查询资源
     * @param id
     * @return
     */
    Resource get(int id);

    /**
     * 查询所有资源
     * @return
     */
    List<Resource> getAll();

    /**
     * 条件查询资源
     * @param page
     * @param resourceQueryParam
     * @return
     */
    IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam);


}
