package com.emmnauel.transactionservice.transaction.mapper;

import com.emmnauel.transactionservice.transaction.application.command.CommandCreateTransaction;
import com.emmnauel.transactionservice.transaction.application.result.TransactionResult;
import com.emmnauel.transactionservice.transaction.domain.Transaction;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTransactionMapper {

    public TransactionResult toResult(Transaction transaction){
        return new TransactionResult(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getBalance(),
                transaction.getStatus(),
                transaction.getType(),
                transaction.getCreatedAt()
        );

    }

}
