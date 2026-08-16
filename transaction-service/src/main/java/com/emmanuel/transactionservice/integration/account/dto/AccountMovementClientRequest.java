package com.emmanuel.transactionservice.integration.account.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountMovementClientRequest(

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        MovementType type
) {
}
