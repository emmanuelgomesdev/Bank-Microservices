package com.emmanuel.accountservice.account.mapper;

import com.emmanuel.accountservice.account.application.command.CommandCreateAccount;
import com.emmanuel.accountservice.account.application.result.AccountBalanceResult;
import com.emmanuel.accountservice.account.application.result.AccountResult;
import com.emmanuel.accountservice.account.dto.AccountBalanceResponse;
import com.emmanuel.accountservice.account.dto.AccountRequest;
import com.emmanuel.accountservice.account.dto.AccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountRestMapper {

    CommandCreateAccount toCommand(AccountRequest request);
    AccountResponse toResponse(AccountResult result);
    AccountBalanceResponse toBalanceResponse(AccountBalanceResult result);
}
