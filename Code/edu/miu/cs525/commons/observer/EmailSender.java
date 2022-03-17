<<<<<<< HEAD:Code/edu/miu/cs525/commons/observer/EmailSender.java
package backend.banking.observer;
=======
package edu.miu.cs525.framework.observer;
>>>>>>> CreateAccount-Bijay:Code/edu/miu/cs525/framework/observer/EmailSender.java

import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountService;
import edu.miu.cs525.commons.AccountTransaction;
import edu.miu.cs525.commons.Log;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.domain.CompanyAccount;
import edu.miu.cs525.framework.domain.PersonalAccount;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EmailSender implements Observer {
    private final AccountService accountService;

    public EmailSender(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {
        boolean emailHeaderAdded = false;
        for (Map.Entry<Account, ArrayList<AccountTransaction>> entry : accountService.getAccountTransactions().entrySet()) {
            Account account = entry.getKey();
            List<AccountTransaction> transactions = entry.getValue();

            int index = 0;
            if (account.getCustomer() instanceof CompanyAccount || account.getCustomer() instanceof PersonalAccount) {
                if (!emailHeaderAdded) {
                    Log.getLogger().write("");
                    Log.getLogger().write("OBSERVER_PATTERN: Pulling changed accounts from AccountService");
                    Log.getLogger().write(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Sending transaction emails ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
                    Log.getLogger().write("|                                                                                       |");

                    emailHeaderAdded = true;
                }

                for (Iterator<AccountTransaction> it = transactions.iterator(); it.hasNext(); ) {
                    AccountTransaction transaction = it.next();

                    if (account.getCustomer() instanceof CompanyAccount) {
                        Log.getLogger().write("    " + index + ". CompanyAccount" + ": " + account.getCustomer().getName() + " [AccNO: " + account.getAccountNumber() + "]");
                        Log.getLogger().write("       Sending email to => " + account.getCustomer().getEmail() + " | " + transaction);
                        Log.getLogger().write(account.getBalance() < 0 ? "       ❌ Negative BALANCE ❌" : "");
                    } else if ((account.getCustomer() instanceof PersonalAccount && account.getBalance() < 0) || (account.getCustomer() instanceof PersonalAccount && transaction.getTranxAmount() > 500)) {
                        Log.getLogger().write("    " + index + ". PersonalAccount" + ": " + account.getCustomer().getName() + " [AccNo: " + account.getAccountNumber() + "]");
                        Log.getLogger().write("       Sending email to => " + account.getCustomer().getEmail() + " | " + transaction);
                        Log.getLogger().write(account.getBalance() < 0 ? "       ❌ Negative BALANCE ❌" : "");
                    }

                    if (it.hasNext()) {
                        Log.getLogger().write("|       ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿       |");
                    }

                    index++;
                }
            }
        }

        if (emailHeaderAdded) {
            Log.getLogger().write("⎩＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ SENT ALL EMAILS ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿⎭");
        }
        accountService.clearChangedAccountList();
    }
}
