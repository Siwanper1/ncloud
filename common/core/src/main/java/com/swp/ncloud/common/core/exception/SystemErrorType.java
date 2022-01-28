package com.swp.ncloud.common.core.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType{

    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001","系统繁忙，请稍后重试"),

    ;

    private String code;
    private String mesg;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

}
