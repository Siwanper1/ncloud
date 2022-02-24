package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User extends BasePo{

    private String name;
    private String mobile;
    private String username;
    private String password;
    private String description;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;

    @TableField(exist = false)
    private Set<Long> roleIds;
    @TableLogic
    private String deleted = "N";

}
