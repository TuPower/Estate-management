package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
    CSKH("Chăm sóc khách hàng"),
    SHOW_VIEW("Dẫn đi xem");

    private final String transactionTypeValue;

    TransactionTypeEnum(String transactionTypeValue) {
        this.transactionTypeValue = transactionTypeValue;
    }

    public String getTransactionTypeValue() {
        return transactionTypeValue;
    }
}
