package com.emmnauel.transactionservice.transaction.dto;

import com.emmnauel.transactionservice.transaction.domain.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(

        @NotNull(message = "Conta ID is required")
        UUID accountId,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotBlank(message = "Message is required")
        @Size(max = 100, message = "Description have must 100 characters")
        String description,

        @NotNull(message = "Type Transaction is required")
        TransactionType type
) {
}
