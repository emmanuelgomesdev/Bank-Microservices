package com.emmanuel.transactionservice.exception;

import com.emmanuel.transactionservice.exception.enums.ErrorResponse;

public class BusinessException extends BaseException{
    public BusinessException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
