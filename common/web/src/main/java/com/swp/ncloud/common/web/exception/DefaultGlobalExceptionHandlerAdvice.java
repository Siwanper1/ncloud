package com.swp.ncloud.common.web.exception;

import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.ncloud.common.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 统一处理系统异常，对于业务模块内的异常，可集成此类，自行处理。
 */
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {BaseException.class})
    public Result baseException(BaseException exception){
        return Result.fail(exception);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception exception) {
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable(Throwable throwable) {
        return Result.fail();
    }
}
