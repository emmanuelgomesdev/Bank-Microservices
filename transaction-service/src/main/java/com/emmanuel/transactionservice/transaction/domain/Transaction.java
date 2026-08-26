package com.emmanuel.transactionservice.transaction.domain;

import com.emmanuel.transactionservice.transaction.domain.enums.TransactionStatus;
import com.emmanuel.transactionservice.transaction.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "amount", precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "balance", precision = 19, scale = 2, nullable = false)
    private BigDecimal balance;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public static Transaction create(
            UUID accountId,
            BigDecimal balance,
            String description,
            TransactionType type
    ){
        Transaction transaction = new Transaction();
        transaction.accountId = accountId;
        transaction.amount = BigDecimal.ZERO;
        transaction.balance = balance;
        transaction.description = description;
        transaction.status = TransactionStatus.PENDING;
        transaction.type = type;

        return transaction;
    }

    public UUID getId() {return id;}

    public UUID getAccountId() {return accountId;}

    public BigDecimal getAmount() {return amount;}

    public String getDescription() {return description;}

    public BigDecimal getBalance() {return balance;}

    public TransactionStatus getStatus() {return status;}

    public TransactionType getType() {return type;}

    public LocalDateTime getCreatedAt() {return createdAt;}

}
