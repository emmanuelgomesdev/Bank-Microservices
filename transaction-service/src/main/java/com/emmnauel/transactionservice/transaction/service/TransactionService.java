package com.emmnauel.transactionservice.transaction.service;

import com.emmnauel.transactionservice.exception.BusinessException;
import com.emmnauel.transactionservice.exception.enums.ErrorResponse;
import com.emmnauel.transactionservice.transaction.application.command.CommandCreateTransaction;
import com.emmnauel.transactionservice.transaction.application.result.TransactionResult;
import com.emmnauel.transactionservice.transaction.domain.Transaction;
import com.emmnauel.transactionservice.transaction.domain.enums.AccountStatus;
import com.emmnauel.transactionservice.transaction.domain.enums.TransactionType;
import com.emmnauel.transactionservice.transaction.mapper.ApplicationTransactionMapper;
import com.emmnauel.transactionservice.transaction.repository.TransactionRepository;
import com.emmnauel.transactionservice.transaction.validation.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final ApplicationTransactionMapper mapper;
    private final TransactionValidator validator;

    public TransactionService(
            TransactionRepository transactionRepository,
            ApplicationTransactionMapper mapper, TransactionValidator validator) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Transactional
    public TransactionResult create(CommandCreateTransaction command) {
        LOGGER. info("Create Transaction with id {}", command.accountId());

        BigDecimal balance = BigDecimal.valueOf(100);

        validateAccountExist(command.accountId());

        validator.validateAmount(command.amount());

        AccountStatus accountStatus = getFakeStatusAccount(command.accountId());

        if (!accountStatus.equals(AccountStatus.ACTIVE)) {
            throw new BusinessException(ErrorResponse.TRANSACTION_ACCOUNT_NOT_ACTIVE);
        }

        BigDecimal newBalance = calculateNewBalance(command.type(), command.amount(), balance);

        Transaction transaction = Transaction.create(
                command.accountId(),
                command.amount(),
                newBalance,
                command.description(),
                command.type()
        );

        var saved = transactionRepository.save(transaction);
        return mapper.toResult(saved);

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

    private AccountStatus getFakeStatusAccount(UUID id) {

        UUID accountId = UUID.fromString("8ad2a0c9-1989-4b89-9728-83ccd96ee18d");

        if (accountId.equals(id)) {
            return AccountStatus.ACTIVE;
        }

        return AccountStatus.CLOSED;

    }

    private BigDecimal calculateNewBalance(
            TransactionType type,
            BigDecimal amount,
            BigDecimal balance) {

        if (type.isCredit()) {

            return balance.add(amount);

        }
        if (amount.compareTo(balance) > 0) {
            throw new BusinessException(ErrorResponse.TRANSACTION_INVALID_AMOUNT);
        }

        return balance.subtract(amount);


    }

}
