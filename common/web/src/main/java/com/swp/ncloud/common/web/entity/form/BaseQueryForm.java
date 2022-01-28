package com.swp.ncloud.common.web.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swp.ncloud.common.web.entity.param.BaseParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 基础查询类，适用于表单查询
 * @param <P>
 */
@Data
@ApiModel
public class BaseQueryForm<P extends BaseParam> extends BaseForm{

    /**
     * 分页查询参数，当前页
     */
    private long current = 1;
    /**
     * 分页查询参数，每页显示的数量
     */
    private long size = 10;

    private Date createdTimeStart;
    private Date createdTimeEnd;

    public P toParam(Class<P> clazz){
        P p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, p);
        return p;
    }

    /**
     * 分页查询，获取page
     * @return
     */
    public Page getPage(){
        return new Page(this.getCurrent(), this.getSize());
    }

}
