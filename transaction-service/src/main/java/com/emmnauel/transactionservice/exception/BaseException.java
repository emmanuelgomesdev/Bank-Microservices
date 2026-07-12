package com.emmnauel.transactionservice.exception;

import com.emmnauel.transactionservice.exception.enums.ErrorResponse;

public class BaseException extends RuntimeException{

    private final ErrorResponse errorResponse;

    public BaseException(ErrorResponse errorResponse) {
        super(errorResponse.getErrorMessage());
        this.errorResponse = errorResponse;
    }


    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
