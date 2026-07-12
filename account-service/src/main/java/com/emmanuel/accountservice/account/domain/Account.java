package com.emmanuel.accountservice.account.domain;

import com.emmanuel.accountservice.account.domain.enums.AccountStatus;
import com.emmanuel.accountservice.exception.BusinessException;
import com.emmanuel.accountservice.exception.enums.ErrorResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public static Account create(
            UUID customerId,
            String branch,
            String number
    ) {
        Account account = new Account();

        account.customerId = customerId;
        account.branch = branch;
        account.number = number;
        account.balance = BigDecimal.ZERO;
        account.status = AccountStatus.ACTIVE;
        account.createdAt = LocalDateTime.now();
        account.updatedAt = LocalDateTime.now();

        return account;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getBranch() {
        return branch;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void block() {
        if (status == AccountStatus.CLOSED) {
            throw new BusinessException(ErrorResponse.ACCOUNT_ALREADY_CLOSED);
        }

        this.status = AccountStatus.BLOCKED;
    }

    public void activate() {
        if (status == AccountStatus.CLOSED) {
            throw new BusinessException(ErrorResponse.ACCOUNT_ALREADY_CLOSED);
        }

        this.status = AccountStatus.ACTIVE;
    }

    public void close() {
        if (this.status == AccountStatus.CLOSED) {
            throw new BusinessException(ErrorResponse.ACCOUNT_ALREADY_CLOSED);
        }

        this.status = AccountStatus.CLOSED;
    }

    public void updateBalance(BigDecimal newBalance) {
        if(newBalance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(ErrorResponse.ACCOUNT_INVALID_BALANCE);
        }

        this.balance = newBalance;
    }
}
