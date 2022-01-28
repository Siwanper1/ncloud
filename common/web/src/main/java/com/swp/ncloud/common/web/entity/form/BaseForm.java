package com.swp.ncloud.common.web.entity.form;

import com.swp.ncloud.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;

/**
 * 基础实体类，用户表单提交
 * @param <T>
 */
@ApiModel
public class BaseForm <T extends BasePo>{

    /**
     * Form 转为 Po
     * @param clazz
     * @return
     */
    public T toPo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this,t);
        return t;
    }

    /**
     * Form 转为 Po
     * @param clazz
     * @return
     */
    public T toPo(long id, Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        t.setId(id);
        BeanUtils.copyProperties(this,t);
        return t;
    }

}
