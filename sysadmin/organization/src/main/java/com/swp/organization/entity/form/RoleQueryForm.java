package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseQueryForm;
import com.swp.organization.entity.param.RoleQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "条件查询角色信息")
public class RoleQueryForm extends BaseQueryForm<RoleQueryParam> {

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("开始查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeStart;
    @ApiModelProperty("结束查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeEnd;
}
