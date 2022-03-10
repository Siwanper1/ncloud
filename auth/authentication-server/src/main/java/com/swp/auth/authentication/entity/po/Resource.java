package com.swp.auth.authentication.entity.po;

import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource extends BasePo {

    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;

}
