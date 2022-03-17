package edu.miu.cs525.creditcard.constant;

public enum CreditCardType {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze");

    public final String name;

    CreditCardType(String name) {
        this.name = name;
    }
}