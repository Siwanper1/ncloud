package com.siwanper.gateway.exception;

import com.swp.ncloud.common.core.exception.ErrorType;
import lombok.Getter;

@Getter
public enum GatewayErrorType implements ErrorType {
    ROUTE_NOT_FOUND("050001", "路由不存在")
    ;
    private String code;
    private String mesg;

    GatewayErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

}
