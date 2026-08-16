package com.emmanuel.accountservice.account.mapper;

import com.emmanuel.accountservice.account.application.result.AccountBalanceResult;
import com.emmanuel.accountservice.account.application.result.AccountMovementResult;
import com.emmanuel.accountservice.account.application.result.AccountResult;
import com.emmanuel.accountservice.account.domain.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApplicationAccountMapper {

    public  AccountResult toResult(Account account){
        return new AccountResult(
                account.getId(),
                account.getCustomerId(),
                account.getBranch(),
                account.getNumber(),
                account.getBalance(),
                account.getStatus(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    public AccountBalanceResult toBalanceResult(Account account){
        return new AccountBalanceResult(
                account.getId(),
                account.getBranch(),
                account.getBranch(),
                account.getBalance(),
                "BRL",
                account.getStatus()
        );
    }

    public AccountMovementResult toMovementResult(
            Account account,
            BigDecimal previousBalance
    ) {
        return new AccountMovementResult(
                account.getId(),
                previousBalance,
                account.getBalance(),
                account.getStatus()
        );
    }
}
