package com.swp.organization.exception;

import com.swp.ncloud.common.core.exception.BaseException;


public class UserNotFoundException extends BaseException{

    public UserNotFoundException(){
        super(OrganizationErrorType.USER_NOT_FOUND);
    }

    public UserNotFoundException(String message){
        super(OrganizationErrorType.USER_NOT_FOUND, message);
    }

}
