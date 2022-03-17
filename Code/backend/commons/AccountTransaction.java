package backend.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        NumberFormat formatter = new DecimalFormat("#0.00");
        return tranxAction + ": [" + formatter.format(tranxAmount) + "] on " + tranxDate.toString();
    }
}
