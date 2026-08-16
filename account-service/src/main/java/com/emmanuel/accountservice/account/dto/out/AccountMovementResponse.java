package com.emmanuel.accountservice.account.dto.out;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMovementResponse(
        UUID accountId,
        BigDecimal previousBalance,
        BigDecimal currentBalance,
        AccountStatus status
) {
}
