package com.emmanuel.accountservice.account.mapper;

import com.emmanuel.accountservice.account.application.command.AccountMovementCommand;
import com.emmanuel.accountservice.account.application.command.CommandCreateAccount;
import com.emmanuel.accountservice.account.application.result.AccountBalanceResult;
import com.emmanuel.accountservice.account.application.result.AccountMovementResult;
import com.emmanuel.accountservice.account.application.result.AccountResult;
import com.emmanuel.accountservice.account.dto.in.AccountMovementRequest;
import com.emmanuel.accountservice.account.dto.out.AccountBalanceResponse;
import com.emmanuel.accountservice.account.dto.in.AccountRequest;
import com.emmanuel.accountservice.account.dto.out.AccountMovementResponse;
import com.emmanuel.accountservice.account.dto.out.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {

    CommandCreateAccount toCommand(AccountRequest request);
    AccountResponse toResponse(AccountResult result);
    AccountBalanceResponse toBalanceResponse(AccountBalanceResult result);

    //Movement
    AccountMovementCommand toCommand(AccountMovementRequest request);
    AccountMovementResponse toResponse(AccountMovementResult result);
}
