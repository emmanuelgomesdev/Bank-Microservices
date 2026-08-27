package com.emmanuel.transactionservice.transaction.controller;

import com.emmanuel.transactionservice.transaction.dto.TransactionRequest;
import com.emmanuel.transactionservice.transaction.dto.TransactionResponse;
import com.emmanuel.transactionservice.transaction.mapper.TransactionRestMapper;
import com.emmanuel.transactionservice.transaction.application.port.input.CreateTransactionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "transactions", description = "Endpoints for transaction management")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final CreateTransactionUseCase useCase;
    private final TransactionRestMapper mapper;

    public TransactionController(CreateTransactionUseCase useCase, TransactionRestMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Create transaction",
            description = "Creates a new transaction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "409", description = "Transaction cannot be processed")
    })
    public ResponseEntity<TransactionResponse> create(
            @RequestBody @Valid TransactionRequest request) {

        var command = mapper.toCommand(request);
        var result = useCase.execute(command);
        var response = mapper.toResult(result);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find transaction by id",
            description = "Finds transaction by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TransactionResponse> findById(@PathVariable UUID id) {
        var result = useCase.findById(id);
        var response = mapper.toResult(result);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(summary = "Find all transactions",
            description = "Finds transactions using pagination parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
    })
    public Page<TransactionResponse> findAll
            (@ParameterObject
             @PageableDefault(
                     page = 0,
                     size = 5,
                     sort = "createdAt",
                     direction = Sort.Direction.DESC
             ) Pageable pageable) {

        var result = useCase.findAll(pageable);
        return result.map(mapper::toResult);

    }


}
