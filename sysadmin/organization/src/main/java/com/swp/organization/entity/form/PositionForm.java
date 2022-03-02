package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("职位信息")
public class PositionForm extends BaseForm<Position> {

    @ApiModelProperty("职位名称")
    @NotBlank(message = "职位名称不能为空")
    private String name;

    @ApiModelProperty("职位描述")
    private String description;

}
