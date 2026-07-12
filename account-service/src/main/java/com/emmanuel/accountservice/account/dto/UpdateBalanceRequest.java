package com.emmanuel.accountservice.account.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateBalanceRequest(
        @NotNull
        @Positive
        BigDecimal newBalance
) {
}
