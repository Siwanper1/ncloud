package com.swp.organization.entity.param;

import com.swp.ncloud.common.web.entity.param.BaseParam;
import com.swp.organization.entity.po.Role;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Data
@ApiIgnore
public class RoleQueryParam extends BaseParam<Role> {

    private String code;
    private String name;

}
