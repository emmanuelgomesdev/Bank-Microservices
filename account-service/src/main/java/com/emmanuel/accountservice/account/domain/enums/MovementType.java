package com.emmanuel.accountservice.account.domain.enums;

public enum MovementType {
    DEPOSIT(true),
    REFUND(true),
    TRANSFER_RECEIVED(true),
    PIX_RECEIVED(true),

    PAYMENT(false),
    WITHDRAW(false),
    TRANSFER_SENT(false),
    PIX_SENT(false);


    private final boolean credit;


    MovementType(boolean credit) {
        this.credit = credit;
    }

    public boolean isCredit() {
        return credit;
    }
}
