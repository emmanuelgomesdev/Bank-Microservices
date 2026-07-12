package com.emmnauel.transactionservice.exception.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
