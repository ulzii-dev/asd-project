package edu.miu.cs525.banking.constant;

public enum BankingAccountType {
    CHECKING("Checking"),
    SAVINGS("Savings");

    public final String name;

    BankingAccountType(String name) {
        this.name = name;
    }
}