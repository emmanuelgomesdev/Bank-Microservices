package com.emmanuel.accountservice.account.dto.in;

import com.emmanuel.accountservice.account.domain.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountMovementRequest(
        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        MovementType type
) {
}
