package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseQueryForm;
import com.swp.organization.entity.param.MenuQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("菜单条件查询参数")
public class MenuQueryForm extends BaseQueryForm<MenuQueryParam> {
    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("开始查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeStart;
    @ApiModelProperty("结束查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeEnd;
}
