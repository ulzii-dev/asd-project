package edu.miu.cs525.framework.observer;

import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.shared.log.Log;

public class ChargeEmailSender implements Observer {
    private final CreditCardAccount account;

    public ChargeEmailSender(CreditCardAccount account) {
        this.account = account;
    }

    @Override
    public void update() {
        if(account != null) {
            Log.getLogger().write(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ ");
            Log.getLogger().write("|                                                                                       |");
            Log.getLogger().write(account.getCustomer().getName() + " [AccNo: " + account.getAccountNumber() + "]");
            Log.getLogger().write("Balance: " + account.getBalance());
            Log.getLogger().write("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ End of Charge the Account ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|");
        }
    }
}
