package com.emmnauel.transactionservice.transaction.application.command;

import com.emmnauel.transactionservice.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CommandCreateTransaction(
        UUID accountId,
        BigDecimal amount,
        String description,
        TransactionType type
) {
}
