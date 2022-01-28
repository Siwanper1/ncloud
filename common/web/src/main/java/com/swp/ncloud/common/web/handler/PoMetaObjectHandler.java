package com.swp.ncloud.common.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.swp.ncloud.common.core.util.UserContextHolder;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class PoMetaObjectHandler implements MetaObjectHandler {

    private String getUserName(){
        return StringUtils.defaultIfBlank(UserContextHolder.getInstance().getUserName(), BasePo.DEFAULT_USERNAME);
    }

    public void insertFill(MetaObject metaObject) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        log.info("time1 : {}", new Date());
        log.info("time2 : {}", Date.from(ZonedDateTime.now().toInstant()));
        this.setInsertFieldValByName("createdBy", getUserName(), metaObject);
        this.setInsertFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.updateFill(metaObject);
    }

    public void updateFill(MetaObject metaObject) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        this.setUpdateFieldValByName("updatedBy", getUserName(), metaObject);
        this.setUpdateFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}
