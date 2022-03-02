package com.swp.organization.entity.po;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("position")
public class Position extends BasePo {
    private String name;
    private String description;
    @TableLogic
    private String deleted = "N";
}
