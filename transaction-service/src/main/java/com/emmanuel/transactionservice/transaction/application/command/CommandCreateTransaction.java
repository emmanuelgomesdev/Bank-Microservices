package com.emmanuel.transactionservice.transaction.application.command;

import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CommandCreateTransaction(
        UUID accountId,
        BigDecimal amount,
        String description,
        TransactionType type
) {
}
