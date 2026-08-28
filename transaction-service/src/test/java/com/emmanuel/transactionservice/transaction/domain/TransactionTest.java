package com.emmanuel.transactionservice.transaction.domain;


import com.emmanuel.transactionservice.exception.BusinessException;
import com.emmanuel.transactionservice.exception.enums.ErrorResponse;
import com.emmanuel.transactionservice.transaction.domain.enums.TransactionStatus;
import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void shouldCompletePendingTransaction() {

        // Arrange: crie uma transação PENDING
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        // Act: execute complete com saldo 1500.00
        transaction.complete(new BigDecimal("1500.00"));

        // Assert:
        // status deve ser COMPLETED
        // balance deve ser 1500.00
        // failureReason deve continuar null

        assertEquals(TransactionStatus.COMPLETED, transaction.getStatus());
        assertEquals(new BigDecimal("1500.00"), transaction.getBalance());
        assertNull(transaction.getFailureReason());

    }

    @Test
    void shouldFailPendingTransaction() {

        // Arrange: criar uma transação PENDING
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        // Act: chamar fail("Account is blocked")

        transaction.fail("Account is blocked");

        // Assert:
        // status deve ser FAILED
        // failureReason deve ser "Account is blocked"
        // balance deve continuar null

        assertEquals(TransactionStatus.FAILED, transaction.getStatus());
        assertEquals("Account is blocked", transaction.getFailureReason());
        assertNull(transaction.getBalance());
    }

    @Test
    void shouldNotFailCompletedTransaction() {

        // Arrange:
        // criar a transação
        // concluir com complete("1500.00")

        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        transaction.complete(new BigDecimal("1500.00"));

        // Act + Assert:
        // tentar fail("Account is blocked")
        // esperar BusinessException

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transaction.fail("Account is blocked")
        );


        // Assert:
        // o status deve continuar COMPLETED
        // o balance deve continuar 1500.00
        // failureReason deve continuar null

        assertEquals(TransactionStatus.COMPLETED, transaction.getStatus());
        assertEquals(new BigDecimal("1500.00"), transaction.getBalance());
        assertEquals(ErrorResponse.TRANSACTION_ALREADY_PROCESSED, exception.getErrorResponse());
        assertNull(transaction.getFailureReason());
    }

    @Test
    void shouldNotCompleteFailedTransaction() {

        // Arrange:
        // criar uma transação PENDING
        // executar fail("Account is blocked")
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        transaction.fail("Account is blocked");

        // Act + Assert:
        // tentar complete(new BigDecimal("1500.00"))
        // capturar BusinessException
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transaction.complete(new BigDecimal("1500.00"))
        );

        // Verificar:
        // erro = TRANSACTION_ALREADY_PROCESSED
        // status continua FAILED
        // failureReason continua "Account is blocked"
        // balance continua null

        assertEquals(ErrorResponse.TRANSACTION_ALREADY_PROCESSED, exception.getErrorResponse());
        assertEquals(TransactionStatus.FAILED, transaction.getStatus());
        assertEquals("Account is blocked", transaction.getFailureReason());
        assertNull(transaction.getBalance());
    }


    @Test
    void shouldNotCompleteTransactionWhenCurrentBalanceIsNull() {

        // Arrange:
        // criar uma transação PENDING
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        // Act + Assert:
        // chamar complete(null)
        // capturar BusinessException
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transaction.complete(null)
        );

        // Verificar:
        // erro = TRANSACTION_CURRENT_BALANCE_REQUIRED
        // status continua PENDING
        // balance continua null
        // failureReason continua null

        assertEquals(ErrorResponse.TRANSACTION_CURRENT_BALANCE_REQUIRED, exception.getErrorResponse());
        assertEquals(TransactionStatus.PENDING, transaction.getStatus());
        assertNull(transaction.getBalance());
        assertNull(transaction.getFailureReason());
    }

    @Test
    void shouldNotFailTransactionWhenReasonIsBlank() {

        // Arrange: criar uma transação PENDING
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );
        // Act + Assert:
        // chamar fail("   ")
        // capturar BusinessException
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transaction.fail("   ")
        );

        // Verificar:
        // erro = TRANSACTION_INVALID_REASON
        // status continua PENDING
        // failureReason continua null
        // balance continua null

        assertEquals(ErrorResponse.TRANSACTION_INVALID_REASON, exception.getErrorResponse());
        assertEquals(TransactionStatus.PENDING, transaction.getStatus());
        assertNull(transaction.getFailureReason());
        assertNull(transaction.getBalance());
    }

    @Test
    void shouldNotFailTransactionWhenReasonIsNull() {
        // criar uma Transaction PENDING
        Transaction transaction = Transaction.create(
                UUID.randomUUID(),
                new BigDecimal("500.00"),
                "Depósito",
                TransactionType.DEPOSIT
        );

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> transaction.fail(null)
        );

        assertEquals(
                ErrorResponse.TRANSACTION_INVALID_REASON,
                exception.getErrorResponse()
        );
        assertEquals(TransactionStatus.PENDING, transaction.getStatus());
        assertNull(transaction.getFailureReason());
        assertNull(transaction.getBalance());
    }

}
