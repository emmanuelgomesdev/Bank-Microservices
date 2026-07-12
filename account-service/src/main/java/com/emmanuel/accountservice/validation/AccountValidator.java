package com.emmanuel.accountservice.validation;

import com.emmanuel.accountservice.account.domain.Account;
import com.emmanuel.accountservice.account.repository.AccountRepository;
import com.emmanuel.accountservice.exception.BusinessException;
import com.emmanuel.accountservice.exception.enums.ErrorResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountValidator {

    private final AccountRepository repository;

    public AccountValidator(AccountRepository repository) {
        this.repository = repository;
    }

    public void validateCustomerIdDoesNotExist(UUID customerId){
        if (repository.existsByCustomerId(customerId)) {
            throw new BusinessException(ErrorResponse.COSTUMER_ALREADY_HAS_ACCOUNT);
        }
    }

    public Account findById(UUID id){
        return repository.findById(id)
                .orElseThrow(()->new BusinessException(ErrorResponse.ACCOUNT_NOT_FOUND));
    }

    public void validateCustomerDoesNotHaveAccount(UUID customerId) {
        if (repository.existsByCustomerId(customerId)) {
            throw new BusinessException(ErrorResponse.COSTUMER_ALREADY_HAS_ACCOUNT);
        }
    }
}
