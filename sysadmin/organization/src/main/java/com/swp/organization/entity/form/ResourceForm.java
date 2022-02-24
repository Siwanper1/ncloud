package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("资源表单信息")
public class ResourceForm extends BaseForm<Resource> {

    @ApiModelProperty(value = "资源编码", example = "user_manager:btn_add")
    @NotBlank(message = "资源编码不能为空")
    private String code;

    @ApiModelProperty("资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty(value = "资源类型", example = "user")
    @NotBlank(message = "资源类型不能为空")
    private String type;

    @ApiModelProperty(value = "资源链接", example = "/user")
    @NotBlank(message = "资源链接不能为空")
    private String url;

    @ApiModelProperty(value = "资源方法类型", example = "POST")
    @NotBlank(message = "资源方法类型不能为空")
    private String method;

    @ApiModelProperty("资源编码")
    private String description;

}
