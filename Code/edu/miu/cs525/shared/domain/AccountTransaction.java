package edu.miu.cs525.shared.domain;

import edu.miu.cs525.framework.constant.AccountOperationConstant;

import java.time.LocalDateTime;

public class AccountTransaction {
    AccountOperationConstant tranxAction;
    double tranxAmount = 0d;
    LocalDateTime tranxDate;

    public AccountTransaction(AccountOperationConstant action, double amount) {
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
