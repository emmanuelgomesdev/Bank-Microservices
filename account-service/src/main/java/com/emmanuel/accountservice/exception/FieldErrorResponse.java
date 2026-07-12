package com.emmanuel.accountservice.exception;

public record FieldErrorResponse(
        String field,
        String message
) {
}
