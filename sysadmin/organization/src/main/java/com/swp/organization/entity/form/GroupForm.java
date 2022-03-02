package com.swp.organization.entity.form;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户组")
public class GroupForm extends BaseForm<Group> {

    @ApiModelProperty("用户组父id")
    @NotNull(message = "用户组父id不能为空")
    private long parentId;
    @ApiModelProperty("用户组名称")
    private String name;
    @ApiModelProperty("用户组描述")
    private String description;

}
