package com.emmanuel.accountservice.exception;

import com.emmanuel.accountservice.exception.enums.ErrorResponse;

public class BusinessException extends BaseException{
    public BusinessException(ErrorResponse response) {
        super(response);
    }
}
