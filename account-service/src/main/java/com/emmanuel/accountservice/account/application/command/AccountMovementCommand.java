package com.emmanuel.accountservice.account.application.command;

import com.emmanuel.accountservice.account.domain.enums.MovementType;

import java.math.BigDecimal;

public record AccountMovementCommand(
        BigDecimal amount,
        MovementType type
) {
}
