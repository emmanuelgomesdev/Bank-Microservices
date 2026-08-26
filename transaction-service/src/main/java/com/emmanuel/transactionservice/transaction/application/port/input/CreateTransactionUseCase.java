package com.emmanuel.transactionservice.transaction.application.port.input;

import com.emmanuel.transactionservice.transaction.application.command.TransactionCommand;
import com.emmanuel.transactionservice.transaction.application.result.TransactionResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CreateTransactionUseCase {

    TransactionResult execute(TransactionCommand command);
    TransactionResult findById(UUID id);
    Page<TransactionResult> findAll(Pageable pageable);

}
