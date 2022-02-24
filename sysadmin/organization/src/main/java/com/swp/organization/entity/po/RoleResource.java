package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("role_resource_relation")
public class RoleResource extends BasePo {

    private long roleId;
    private long resourceId;

}
