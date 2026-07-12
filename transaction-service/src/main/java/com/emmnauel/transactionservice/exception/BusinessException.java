package com.emmnauel.transactionservice.exception;

import com.emmnauel.transactionservice.exception.enums.ErrorResponse;

public class BusinessException extends BaseException{
    public BusinessException(ErrorResponse errorResponse) {
        super(errorResponse);
    }
}
