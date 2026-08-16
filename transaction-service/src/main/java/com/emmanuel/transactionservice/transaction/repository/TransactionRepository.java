package com.emmanuel.transactionservice.transaction.repository;

import com.emmanuel.transactionservice.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
