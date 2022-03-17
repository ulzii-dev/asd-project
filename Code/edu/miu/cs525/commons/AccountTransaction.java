package edu.miu.cs525.commons;

import java.time.LocalDateTime;

public class AccountTransaction {
    Action tranxAction;
    double tranxAmount = 0d;
    LocalDateTime tranxDate;

    public AccountTransaction(Action action, double amount) {
        tranxAction = action;
        tranxAmount = amount;
        tranxDate = LocalDateTime.now();
    }

    public double getTranxAmount() {
        return tranxAmount;
    }

    public String toString() {
        return tranxAction + ": [" + tranxAmount + "] on " + tranxDate.toString();
    }
}
