package edu.miu.cs525.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountTransaction {
    Action transactionAction;
    double transactionAmount = 0d;
    LocalDateTime transactionDate;

    public AccountTransaction(Action action, double amount) {
        transactionAction = action;
        transactionAmount = amount;
        transactionDate = LocalDateTime.now();
    }

    public double getTranxAmount() {
        return transactionAmount;
    }

    public Action getTransactionAction(){
        return transactionAction;
    }

    public String toString() {
        NumberFormat formatter = new DecimalFormat("#00.00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return transactionAction + ": [" + formatter.format(transactionAmount) + "] on " + dateTimeFormatter.format(transactionDate);
    }
}
