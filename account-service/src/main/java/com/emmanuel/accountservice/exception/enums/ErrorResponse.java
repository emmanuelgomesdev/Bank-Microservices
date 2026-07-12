package com.emmanuel.accountservice.exception.enums;

import org.springframework.http.HttpStatus;

public enum ErrorResponse {
    COSTUMER_ALREADY_HAS_ACCOUNT("ACCOUNT-001", "Customer already has account", HttpStatus.CONFLICT),
    ACCOUNT_NOT_FOUND("ACCOUNT-002" ,"Account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_NUMBER_ALREADY_EXISTS("ACCOUNT-003", "Account number already exists", HttpStatus.BAD_REQUEST),
    ACCOUNT_VALIDATION_ERROR("ACCOUNT-004", "Account validation error", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_CLOSED("ACCOUNT-005", "Account already closed", HttpStatus.CONFLICT),
    TRANSACTION_INSUFFICIENT_BALANCE("ACCOUNT-006", "Insufficient balance", HttpStatus.BAD_REQUEST),
    ACCOUNT_INVALID_BALANCE("ACCOUNT-007", "ACCOUNT_INVALID_BALANCE", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    ErrorResponse(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
