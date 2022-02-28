package com.swp.organization.entity.param;

import com.swp.ncloud.common.web.entity.param.BaseParam;
import com.swp.organization.entity.po.Menu;
import lombok.Data;

@Data
public class MenuQueryParam extends BaseParam<Menu> {

    private String name;

}
