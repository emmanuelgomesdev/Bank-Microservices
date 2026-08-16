package com.emmanuel.transactionservice.transaction.mapper;

import com.emmanuel.transactionservice.transaction.application.command.CommandCreateTransaction;
import com.emmanuel.transactionservice.transaction.application.result.TransactionResult;
import com.emmanuel.transactionservice.transaction.dto.TransactionRequest;
import com.emmanuel.transactionservice.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {

    CommandCreateTransaction toCommand(TransactionRequest request);
    TransactionResponse toResult(TransactionResult result);

}
