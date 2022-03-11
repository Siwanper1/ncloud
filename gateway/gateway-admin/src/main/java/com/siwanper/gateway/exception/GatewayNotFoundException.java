package com.siwanper.gateway.exception;

import com.swp.ncloud.common.core.exception.BaseException;

public class GatewayNotFoundException extends BaseException {

    public GatewayNotFoundException(){
        super(GatewayErrorType.ROUTE_NOT_FOUND);
    }
}
