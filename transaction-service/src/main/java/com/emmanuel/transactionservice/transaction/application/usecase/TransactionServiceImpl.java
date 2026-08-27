package com.emmanuel.transactionservice.transaction.application.usecase;

import com.emmanuel.transactionservice.exception.BusinessException;
import com.emmanuel.transactionservice.exception.enums.ErrorResponse;
import com.emmanuel.transactionservice.transaction.application.command.TransactionCommand;
import com.emmanuel.transactionservice.transaction.application.result.TransactionResult;
import com.emmanuel.transactionservice.transaction.domain.Transaction;
import com.emmanuel.transactionservice.transaction.mapper.ApplicationTransactionMapper;
import com.emmanuel.transactionservice.transaction.repository.TransactionRepository;
import com.emmanuel.transactionservice.transaction.application.port.input.CreateTransactionUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements CreateTransactionUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final ApplicationTransactionMapper mapper;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            ApplicationTransactionMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public TransactionResult execute(TransactionCommand command) {
        LOGGER.info(
                "Recebida solicitação de transação para a conta {}",
                command.accountId()
        );

        Transaction transaction = Transaction.create(
                command.accountId(),
                command.amount(),
                command.description(),
                command.type()
        );

        var savedTransaction = transactionRepository.save(transaction);

        LOGGER.info(
                "Transação {} registrada para a conta {} com status {}",
                savedTransaction.getId(),
                savedTransaction.getAccountId(),
                savedTransaction.getStatus()
        );
        return mapper.toResult(savedTransaction);

    }

    @Override
    public TransactionResult findById(UUID id) {
        LOGGER.info("Busca conta com id: {}", id);

        var found = transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorResponse.TRANSACTION_ACCOUNT_NOT_FOUND));
        return mapper.toResult(found);

    }

    @Override
    public Page<TransactionResult> findAll(Pageable pageable) {
        LOGGER.info("Busca todas as transações com paginação {}", pageable);
        return transactionRepository.findAll(pageable)
                .map(mapper::toResult);
    }

}
