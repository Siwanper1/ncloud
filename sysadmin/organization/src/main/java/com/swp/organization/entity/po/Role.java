package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("roles")
public class Role extends BasePo {

    private String code;
    private String name;
    private String description;

    @TableLogic
    private String deleted = "N";
    @TableField(exist = false)
    private Set<Long> resourceIds;


}
