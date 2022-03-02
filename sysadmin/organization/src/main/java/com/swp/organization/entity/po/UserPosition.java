package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_position_relation")
public class UserPosition extends BasePo {
    private long userId;
    private long positionId;
}
