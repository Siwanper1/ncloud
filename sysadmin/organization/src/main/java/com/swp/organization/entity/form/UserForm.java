package com.swp.organization.entity.form;


import com.swp.ncloud.common.web.entity.form.BaseForm;
import com.swp.organization.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@ApiModel(description = "添加用户信息")
public class UserForm extends BaseForm<User> {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度1～20个字符")
    private String username;

    @ApiModelProperty("用户密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "用户名长度6～20个字符")
    private String password;

    @ApiModelProperty("用户姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("用户电话")
    @NotBlank(message = "电话不能为空")
    private String mobile;

    @ApiModelProperty("用户描述信息")
    private String description;

    @ApiModelProperty("用户是否可用，默认可用")
    private Boolean enabled = true;

    @ApiModelProperty("用户账号是否过期，默认未过期")
    private Boolean accountNonExpired = true;

    @ApiModelProperty("用户密码是否过期，默认未过期")
    private Boolean credentialsNonExpired = true;

    @ApiModelProperty("用户是否被锁定，默认未锁定")
    private Boolean accountNonLocked = true;

    @ApiModelProperty("用户角色ID")
    private Set<Long> roleIds;

}
