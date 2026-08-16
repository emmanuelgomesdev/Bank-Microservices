package com.emmanuel.transactionservice.transaction.mapper;

import com.emmanuel.transactionservice.transaction.application.result.TransactionResult;
import com.emmanuel.transactionservice.transaction.domain.Transaction;
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
