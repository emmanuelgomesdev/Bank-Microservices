package com.emmanuel.transactionservice.integration.account.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMovementClientResponse(

        UUID accountId,
        BigDecimal previousBalance,
        BigDecimal currentBalance

) {
}
