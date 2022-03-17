package edu.miu.cs525.creditcard.observer;

import edu.miu.cs525.commons.Log;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.framework.Observer;

public class EmailSender implements Observer {
    private final CreditCardAccount account;

    public EmailSender(CreditCardAccount account) {
        this.account = account;
    }

    @Override
    public void update() {
        if(account != null) {
            Log.getLogger().write(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ ");
            Log.getLogger().write("|                                                                                       |");
            Log.getLogger().write(account.getCustomer().getName() + " [AccNo: " + account.getAccountNumber() + "]");
            Log.getLogger().write("Balance: " + account.getNewBalance());
            Log.getLogger().write("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ End of Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
        }
    }
}