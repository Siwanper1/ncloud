package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ApiModel(value = "更新角色信息")
public class RoleUpdateForm extends BaseForm<Role> {

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("角色的资源")
    private Set<Long> resourceIds;

}
