package com.emmnauel.transactionservice.transaction.domain.enums;

public enum TransactionType {
    DEPOSIT(true),
    WITHDRAW(true),
    PIX_SENT(true),
    PIX_RECEIVED(true),
    PAYMENT(false),
    REFUND(false),
    TRANSFER_SENT(false),
    TRANSFER_RECEIVED(false);

    private final boolean credit;

    TransactionType(boolean credit) {
        this.credit = credit;
    }

    public boolean isCredit() {
        return credit;
    }
}
