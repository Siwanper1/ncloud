package com.swp.ncloud.common.core.exception;

public interface ErrorType {

    /**
     * 错误编码
     * @return
     */
    String getCode();

    /**
     * 错误信息
     * @return
     */
    String getMesg();

}
