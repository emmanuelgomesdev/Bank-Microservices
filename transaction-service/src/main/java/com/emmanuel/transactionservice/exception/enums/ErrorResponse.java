package com.emmanuel.transactionservice.exception.enums;

import org.springframework.http.HttpStatus;

public enum ErrorResponse {

    TRANSACTION_NOT_FOUND("TRANSACTION-001", "Transaction not found", HttpStatus.NOT_FOUND),
    TRANSACTION_ACCOUNT_NOT_FOUND("TRANSACTION-002", "Account not found", HttpStatus.NOT_FOUND),
    TRANSACTION_ACCOUNT_NOT_ACTIVE("TRANSACTION-003", "Account is not active", HttpStatus.CONFLICT),
    TRANSACTION_ACCOUNT_BLOCKED("TRANSACTION-004", "Account is blocked", HttpStatus.CONFLICT),
    TRANSACTION_ACCOUNT_CLOSED("TRANSACTION-005", "Account is closed", HttpStatus.CONFLICT),
    TRANSACTION_INSUFFICIENT_BALANCE("TRANSACTION-006", "Insufficient balance", HttpStatus.CONFLICT),
    TRANSACTION_INVALID_AMOUNT("TRANSACTION-007", "Invalid transaction amount", HttpStatus.BAD_REQUEST),
    TRANSACTION_INVALID_TYPE("TRANSACTION-008", "Invalid transaction type", HttpStatus.BAD_REQUEST),
    TRANSACTION_ALREADY_PROCESSED("TRANSACTION-009", "Transaction already processed", HttpStatus.CONFLICT),
    TRANSACTION_CANCELED("TRANSACTION-010", "Transaction has been canceled", HttpStatus.CONFLICT),
    TRANSACTION_FAILED("TRANSACTION-011", "Transaction failed", HttpStatus.UNPROCESSABLE_ENTITY),
    TRANSACTION_VALIDATION_ERROR("TRANSACTION-012", "Validation error", HttpStatus.BAD_REQUEST),
    TRANSACTION_INTERNAL_ERROR("TRANSACTION-013", "An unexpected internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    TRANSACTION_DESCRIPTION_REQUIRED("TRANSACTION-013", "Transaction description must not be null or blank", HttpStatus.BAD_REQUEST),
    TRANSACTION_ACCOUNT_REQUIRED("TRANSACTION-014", "Transaction account ID is required", HttpStatus.BAD_REQUEST),
    TRANSACTION_INVALID_REASON("TRANSACTION-015", "The provided reason is invalid.", HttpStatus.BAD_REQUEST),
    TRANSACTION_CURRENT_BALANCE_REQUIRED("TRANSACTION-016", "Current balance is required to complete the transaction", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;


    ErrorResponse(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
