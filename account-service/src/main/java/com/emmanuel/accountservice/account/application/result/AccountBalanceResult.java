package com.emmanuel.accountservice.account.application.result;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountBalanceResult(
        UUID accountId,
        String branch,
        String number,
        BigDecimal balance,
        String currency,
        AccountStatus status
) {
}
