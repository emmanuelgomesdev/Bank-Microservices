package com.emmanuel.transactionservice.integration.client;

import com.emmanuel.transactionservice.integration.account.dto.AccountMovementClientRequest;
import com.emmanuel.transactionservice.integration.account.dto.AccountMovementClientResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "account-service",
        url = "${account-service.url}")
public interface AccountClient {

    @PostMapping("/accounts/{id}/movement")
    AccountMovementClientResponse applyMovement(@PathVariable("id")
            UUID id, @RequestBody @Valid AccountMovementClientRequest request);


}
