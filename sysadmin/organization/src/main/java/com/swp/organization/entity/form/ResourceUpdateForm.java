package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "更新资源信息")
public class ResourceUpdateForm extends BaseForm<Resource> {
    @ApiModelProperty(value = "资源编码", example = "user_manager:btn_add")
    private String code;

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty(value = "资源类型", example = "user")
    private String type;

    @ApiModelProperty(value = "资源链接", example = "/user")
    private String url;

    @ApiModelProperty(value = "资源方法类型", example = "POST")
    private String method;

    @ApiModelProperty("资源编码")
    private String description;
}
