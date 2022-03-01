package com.swp.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("menu")
public class Menu extends BasePo {

    private long parentId;
    private String type;
    private String href;
    private String icon;
    private String name;
    private String description;
    private int orderNum;

    @TableLogic
    private String deleted = "N";
    @TableField(exist = false)
    private List<Menu> child;


}
