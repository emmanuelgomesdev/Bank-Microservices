package com.emmanuel.accountservice.exception;

import com.emmanuel.accountservice.exception.enums.ErrorResponse;

public class BaseException extends RuntimeException{

   private final ErrorResponse response;


    public BaseException(ErrorResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ErrorResponse getResponse() {
        return response;
    }
}
