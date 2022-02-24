package com.swp.organization.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swp.ncloud.common.web.entity.vo.BaseVo;
import com.swp.organization.entity.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Set;

@ApiIgnore
@Data
@NoArgsConstructor
public class UserVo extends BaseVo<User> {

    public UserVo(User user){
        BeanUtils.copyProperties(user, this);
    }

    private String name;
    private String mobile;
    private String username;
    private String description;
    private Set<Long> roleIds;
    private String deleted;
    private String createdBy;
    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
}
