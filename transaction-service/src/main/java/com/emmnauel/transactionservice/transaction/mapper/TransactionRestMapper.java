package com.emmnauel.transactionservice.transaction.mapper;

import com.emmnauel.transactionservice.transaction.application.command.CommandCreateTransaction;
import com.emmnauel.transactionservice.transaction.application.result.TransactionResult;
import com.emmnauel.transactionservice.transaction.dto.TransactionRequest;
import com.emmnauel.transactionservice.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {

    CommandCreateTransaction toCommand(TransactionRequest request);
    TransactionResponse toResult(TransactionResult result);

}
