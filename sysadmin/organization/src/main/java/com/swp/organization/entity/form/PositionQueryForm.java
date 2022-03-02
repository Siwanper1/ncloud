package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseQueryForm;
import com.swp.organization.entity.param.PositionQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("条件查询")
public class PositionQueryForm extends BaseQueryForm<PositionQueryParam> {

    @ApiModelProperty("职位名称")
    private String name;

    @ApiModelProperty("开始查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeStart;
    @ApiModelProperty("结束查询日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdTimeEnd;

}
