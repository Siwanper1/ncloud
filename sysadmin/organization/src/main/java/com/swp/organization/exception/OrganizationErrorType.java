package com.swp.organization.exception;

import com.swp.ncloud.common.core.exception.ErrorType;
import lombok.Getter;

@Getter
public enum OrganizationErrorType implements ErrorType {

    USER_NOT_FOUND("030001","用户不存在"),
    ROLE_NOT_FOUND("030002","角色不存在"),
    RESOURCE_NOT_FOUND("030003","资源不存在"),
    MENU_NOT_FOUND("030004","菜单不存在"),
    POSITION_NOT_FOUND("030005","职位不存在"),
    GROUP_NOT_FOUND("030006","用户不存在"),
    ;

    private String code;
    private String mesg;

    OrganizationErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
