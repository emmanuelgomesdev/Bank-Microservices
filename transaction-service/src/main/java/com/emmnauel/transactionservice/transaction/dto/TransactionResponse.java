package com.emmnauel.transactionservice.transaction.dto;

import com.emmnauel.transactionservice.transaction.domain.enums.TransactionStatus;
import com.emmnauel.transactionservice.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
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
