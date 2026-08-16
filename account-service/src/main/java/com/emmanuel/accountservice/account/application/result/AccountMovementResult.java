package com.emmanuel.accountservice.account.application.result;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMovementResult(
        UUID accountId,
        BigDecimal previousBalance,
        BigDecimal currentBalance,
        AccountStatus status
) {
}
