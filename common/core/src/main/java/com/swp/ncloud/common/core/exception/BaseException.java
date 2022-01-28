package com.swp.ncloud.common.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final ErrorType errorType;

    /**
     * 默认返回系统错误
     */
    public BaseException(){
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause){
        super(message, cause);
        this.errorType = errorType;
    }
}
