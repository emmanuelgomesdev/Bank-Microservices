package com.emmanuel.accountservice.account.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AccountRequest(

        @NotNull(message = "Customer ID is required")
        UUID customerId
) {
}
