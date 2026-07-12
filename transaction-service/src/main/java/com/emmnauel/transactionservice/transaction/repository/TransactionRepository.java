package com.emmnauel.transactionservice.transaction.repository;

import com.emmnauel.transactionservice.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
