package com.emmanuel.transactionservice.transaction.service;

import com.emmanuel.transactionservice.exception.BusinessException;
import com.emmanuel.transactionservice.exception.enums.ErrorResponse;
import com.emmanuel.transactionservice.integration.account.dto.AccountMovementClientRequest;
import com.emmanuel.transactionservice.integration.account.dto.MovementType;
import com.emmanuel.transactionservice.integration.client.AccountClient;
import com.emmanuel.transactionservice.transaction.application.command.CommandCreateTransaction;
import com.emmanuel.transactionservice.transaction.application.result.TransactionResult;
import com.emmanuel.transactionservice.transaction.domain.Transaction;
import com.emmanuel.transactionservice.transaction.mapper.ApplicationTransactionMapper;
import com.emmanuel.transactionservice.transaction.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final ApplicationTransactionMapper mapper;
    private final AccountClient accountClient;

    public TransactionService(
            TransactionRepository transactionRepository,
            ApplicationTransactionMapper mapper, AccountClient accountClient) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
        this.accountClient = accountClient;
    }

    @Transactional
    public TransactionResult create(CommandCreateTransaction command) {
        LOGGER.info("Creating transaction for account {}", command.accountId());

        var movementRequest = new AccountMovementClientRequest(
                command.amount(),
                MovementType.valueOf(command.type().name())
        );

       var movementResponse =
               accountClient.applyMovement(command.accountId(), movementRequest);

       var transaction = Transaction.create(
               command.accountId(),
               command.amount(),
               movementResponse.currentBalance(),
               command.description(),
               command.type()
       );

       var savedTransaction = transactionRepository.save(transaction);

       return mapper.toResult(savedTransaction);

    }

    @Transactional(readOnly = true)
    public TransactionResult findById(UUID id) {
        LOGGER.info("Find Transaction with id {}", id);

        var found = transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorResponse.TRANSACTION_NOT_FOUND));

        return mapper.toResult(found);

    }

    @Transactional(readOnly = true)
    public Page<TransactionResult> findAll(Pageable pageable) {
        LOGGER.info("Find All Transactions with Pageable {}", pageable);

        return transactionRepository.findAll(pageable)
                .map(mapper::toResult);

    }

    private void validateAccountExist(UUID id) {
        UUID fakeAccountId = UUID.fromString("8ad2a0c9-1989-4b89-9728-83ccd96ee18d");

        if (!id.equals(fakeAccountId)) {
            throw new BusinessException(ErrorResponse.TRANSACTION_NOT_FOUND);
        }
    }


}
