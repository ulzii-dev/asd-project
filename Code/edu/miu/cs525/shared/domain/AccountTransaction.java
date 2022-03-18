package edu.miu.cs525.shared.domain;

import edu.miu.cs525.framework.constant.AccountOperationConstant;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountTransaction {
    AccountOperationConstant tranxOperation;
    double transactionAmount = 0d;
    LocalDateTime transactionDate;

    public AccountTransaction(AccountOperationConstant action, double amount) {
        tranxOperation = action;
        transactionAmount = amount;
        transactionDate = LocalDateTime.now();
    }

    public double getTranxAmount() {
        return transactionAmount;
    }

    public AccountOperationConstant getTransactionOperation() {
        return tranxOperation;
    }

    public String toString() {
        NumberFormat formatter = new DecimalFormat("#00.00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return tranxOperation + ": [" + formatter.format(transactionAmount) + "] on " + dateTimeFormatter.format(transactionDate);
    }
}
