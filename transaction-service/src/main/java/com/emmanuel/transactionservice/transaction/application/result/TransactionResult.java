package com.emmanuel.transactionservice.transaction.application.result;

import com.emmanuel.transactionservice.transaction.domain.enums.TransactionStatus;
import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResult(
        UUID id,
        UUID contaId,
        BigDecimal amount,
        String description,
        BigDecimal balance,
        TransactionStatus status,
        TransactionType type,
        LocalDateTime created_at
) {
}
