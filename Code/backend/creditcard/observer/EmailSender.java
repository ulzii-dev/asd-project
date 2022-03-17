package backend.creditcard.observer;

import backend.commons.Log;
import backend.creditcard.CreditCardAccount;
import framework.Observer;

public class EmailSender implements Observer {
    private final CreditCardAccount account;

    public EmailSender(CreditCardAccount account) {
        this.account = account;
    }

    @Override
    public void update() {
        Log.getLogger().write(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ ");
        Log.getLogger().write("|                                                                                       |");
        Log.getLogger().write(account.getCustomer().getName() + " [AccNo: " + account.getAccountNumber() + "]");
        Log.getLogger().write("Balance: " + account.getNewBalance());
        Log.getLogger().write("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ End of Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
    }
}
