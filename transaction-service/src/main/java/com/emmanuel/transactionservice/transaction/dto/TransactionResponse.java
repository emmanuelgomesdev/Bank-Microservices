package com.emmanuel.transactionservice.transaction.dto;

import com.emmanuel.transactionservice.transaction.domain.enums.TransactionStatus;
import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        String description,
        BigDecimal balance,
        TransactionStatus status,
        TransactionType type,
        LocalDateTime createdAt
) {
}
