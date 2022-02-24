package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("resource")
public class Resource extends BasePo {

    private String code;
    private String name;
    private String type;
    private String url;
    private String method;
    @TableLogic
    private String deleted;
    private String description;

}
