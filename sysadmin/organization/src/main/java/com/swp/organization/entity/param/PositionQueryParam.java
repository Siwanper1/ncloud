package com.swp.organization.entity.param;

import com.swp.ncloud.common.web.entity.param.BaseParam;
import com.swp.organization.entity.po.Position;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Data
@ApiIgnore
public class PositionQueryParam extends BaseParam<Position> {

    private String name;

}
