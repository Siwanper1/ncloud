package com.swp.ncloud.common.web.entity.vo;

import com.swp.ncloud.common.web.entity.po.BasePo;
import lombok.Data;

/**
 * 基础类，用于和前端进行交互，过滤字段后传给前端展示
 * @param <T>
 */
@Data
public class BaseVo <T extends BasePo>{
    private long id;
}
