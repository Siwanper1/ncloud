package com.swp.ncloud.common.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * 基础类，查询参数
 * @param <T>
 */
@Data
public class BaseParam<T extends BasePo> {

    private Date createdTimeStart;
    private Date createdTimeEnd;

    public QueryWrapper<T> build(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.ge(null != this.createdTimeStart, "created_time", this.createdTimeStart);
        queryWrapper.le(null != this.createdTimeEnd, "created_time", this.createdTimeEnd);
        return queryWrapper;
    }

}
