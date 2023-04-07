package com.example.demo.enumerator;

public enum TransactionType {
    DEPOSIT ("Deposit"),
    WITHDRAW ("Withdraw"),
    TRANSFER ("Transfer");
    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
