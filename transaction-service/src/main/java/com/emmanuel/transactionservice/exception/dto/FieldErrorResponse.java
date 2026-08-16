package com.emmanuel.transactionservice.exception.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
