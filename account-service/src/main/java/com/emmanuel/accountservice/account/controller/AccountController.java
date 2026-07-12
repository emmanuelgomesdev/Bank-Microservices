package com.emmanuel.accountservice.account.controller;

import com.emmanuel.accountservice.account.domain.Account;
import com.emmanuel.accountservice.account.dto.AccountBalanceResponse;
import com.emmanuel.accountservice.account.dto.AccountRequest;
import com.emmanuel.accountservice.account.dto.AccountResponse;
import com.emmanuel.accountservice.account.dto.UpdateBalanceRequest;
import com.emmanuel.accountservice.account.mapper.AccountRestMapper;
import com.emmanuel.accountservice.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Tag(name = "accounts", description = "Endpoints for account management")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRestMapper accountRestMapper;

    public AccountController(AccountService accountService, AccountRestMapper accountRestMapper) {
        this.accountService = accountService;
        this.accountRestMapper = accountRestMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create account",
            description = "Creates a new account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "409", description = "Account already exists")
    })
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountRequest request) {

        var command = accountRestMapper.toCommand(request);
        var result = accountService.create(command);
        var response = accountRestMapper.toResponse(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find account by id",
            description = "Finds account by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AccountResponse> findById(@PathVariable UUID id) {

        var result = accountService.findById(id);
        var response = accountRestMapper.toResponse(result);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Find all accounts",
            description = "Finds accounts using pagination parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts found"),
    })
    public Page<AccountResponse> findAll(
            @ParameterObject
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "customerId"
            )
            Pageable pageable) {

        var result = accountService.findAll(pageable);
        return result.map(accountRestMapper::toResponse);
    }

    @PatchMapping(value = "/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Block account", description = "Blocks an active account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account blocked successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "409", description = "Account already closed")
    })
    public ResponseEntity<AccountResponse> block(@PathVariable UUID id) {
        var result = accountService.block(id);
        var response = accountRestMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/{id}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Unblock account", description = "Unblocks an active account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account unblock successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "409", description = "Account already closed")
    })
    public ResponseEntity<AccountResponse> active(@PathVariable UUID id) {
        var result = accountService.activate(id);
        var response = accountRestMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/{id}/close", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Close account", description = "Close an active account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account closed successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "409", description = "Account already closed")
    })
    public ResponseEntity<AccountResponse> close(@PathVariable UUID id) {
        var result = accountService.close(id);
        var response = accountRestMapper.toResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}/balance")
    @Operation(summary = "Get account balance", description = "Returns the current account balance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<AccountBalanceResponse> Balance(@PathVariable UUID id) {
        var result = accountService.getBalance(id);
        var response = accountRestMapper.toBalanceResponse(result);
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}/balance")
    @Operation(summary = "Update balance", description = "Updates the current balance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance successfully updated"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<AccountBalanceResponse> updateBalance(
            @PathVariable UUID id, @RequestBody @Valid UpdateBalanceRequest request) {

        var result  = accountService.updateBalance(id, request.newBalance());
        var response = accountRestMapper.toBalanceResponse(result);
        return ResponseEntity.ok(response);
    }

}
