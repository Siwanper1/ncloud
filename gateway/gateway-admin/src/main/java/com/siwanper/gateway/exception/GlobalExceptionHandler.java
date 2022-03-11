package com.siwanper.gateway.exception;

import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.ncloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler({GatewayNotFoundException.class})
    public Result routeNotFound(GatewayNotFoundException exception){
        log.error("exception: {}", exception.getMessage());
        return Result.fail(exception);
    }

}
