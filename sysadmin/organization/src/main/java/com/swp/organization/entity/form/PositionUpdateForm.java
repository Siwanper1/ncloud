package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("更新职位信息")
public class PositionUpdateForm extends BaseForm<Position> {

    @ApiModelProperty("职位名称")
    private String name;

    @ApiModelProperty("职位描述")
    private String description;

}
