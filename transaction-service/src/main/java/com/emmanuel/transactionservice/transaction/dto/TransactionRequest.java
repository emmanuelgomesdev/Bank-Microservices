package com.emmanuel.transactionservice.transaction.dto;

import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(

        @NotNull(message = "Account ID is required")
        UUID accountId,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
        @Digits(integer = 17, fraction = 2, message = "Amount must have up to 17 integer digits and 2 decimal places")
        BigDecimal amount,

        @NotBlank(message = "Transaction description is required")
        @Size(max = 100, message = "Transaction description must not exceed 100 characters")
        String description,

        @NotNull(message = "Transaction type is required")
        TransactionType type
) {
}
