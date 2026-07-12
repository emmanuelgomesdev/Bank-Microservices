package com.emmnauel.transactionservice.transaction.validation;

import com.emmnauel.transactionservice.exception.BusinessException;
import com.emmnauel.transactionservice.exception.enums.ErrorResponse;

import java.math.BigDecimal;

public class TransactionValidator {

    public void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorResponse.TRANSACTION_INVALID_AMOUNT);
        }
    }
}


