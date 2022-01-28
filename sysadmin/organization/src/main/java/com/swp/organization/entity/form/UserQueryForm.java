package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseQueryForm;
import com.swp.organization.entity.param.UserQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "查询用户信息")
public class UserQueryForm extends BaseQueryForm<UserQueryParam> {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户电话")
    private String mobile;

    @ApiModelProperty("开始查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeStart;
    @ApiModelProperty("结束查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeEnd;
}
