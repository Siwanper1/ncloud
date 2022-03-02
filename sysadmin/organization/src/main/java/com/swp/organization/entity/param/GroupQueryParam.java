package com.swp.organization.entity.param;

import com.swp.ncloud.common.web.entity.param.BaseParam;
import com.swp.organization.entity.po.Group;
import lombok.Data;

@Data
public class GroupQueryParam extends BaseParam<Group> {
    private String name;
}
