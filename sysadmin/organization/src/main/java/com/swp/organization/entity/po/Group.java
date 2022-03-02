package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_groups")
public class Group extends BasePo {
    private long parentId;
    private String name;
    private String description;
    @TableLogic
    private String deleted = "N";
    @TableField(exist = false)
    private List<Group> child;




}
