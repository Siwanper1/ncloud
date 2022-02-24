package com.swp.organization.exception;

import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.ncloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import com.swp.organization.entity.po.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice{

    @ExceptionHandler(value = {UserNotFoundException.class})
    public Result userNotFound(UserNotFoundException exception){
        log.error(exception.getMessage());
        return Result.fail(exception);
    }

    @ExceptionHandler(value = {RoleNotFoundException.class})
    public Result roleNotFound(RoleNotFoundException exception){
        log.error(exception.getMessage());
        return Result.fail(exception);
    }


}
