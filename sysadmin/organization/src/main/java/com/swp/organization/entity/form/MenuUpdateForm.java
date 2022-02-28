package com.swp.organization.entity.form;

import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuUpdateForm extends BaseForm<Menu> {
    @ApiModelProperty("父菜单")
    private long parentId;
    @ApiModelProperty("菜单类型")
    private String type;
    @ApiModelProperty("菜单链接")
    private String href;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("菜单名称")
    private String name;
    @ApiModelProperty("菜单描述")
    private String description;
    @ApiModelProperty("菜单排序")
    private int orderNum;
}
