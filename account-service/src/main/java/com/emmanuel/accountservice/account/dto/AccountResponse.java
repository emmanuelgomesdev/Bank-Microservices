package com.emmanuel.accountservice.account.dto;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        UUID customerId,
        String branch,
        String number,
        BigDecimal balance,
        AccountStatus status
) {
}
