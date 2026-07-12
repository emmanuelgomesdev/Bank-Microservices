package com.emmanuel.accountservice.account.application.result;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResult(
        UUID id,
        UUID customerId,
        String branch,
        String number,
        BigDecimal balance,
        AccountStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
