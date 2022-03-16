package backend.creditcard.observer;

import backend.commons.*;
import framework.Observer;
import ui.CompanyAccount;
import ui.PersonalAccount;

import java.lang.reflect.Array;
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
            if(account.getCustomer() instanceof CompanyAccount || account.getCustomer() instanceof PersonalAccount) {
                if(!emailHeaderAdded) {
                    Log.getLogger().write("OBSERVER_PATTERN: Pulling changed accounts from AccountService");
                    Log.getLogger().write(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ Sending transaction emails ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
                    Log.getLogger().write("|                                                                                       |");

                    emailHeaderAdded = true;
                }
                for (Iterator<AccountTransaction> it = transactions.iterator(); it.hasNext(); ) {
                    AccountTransaction transaction = it.next();

                    if(account.getCustomer() instanceof CompanyAccount) {
                        Log.getLogger().write( index + ".    CompanyAccount");
                        Log.getLogger().write( "      Sending email to => " + account.getCustomer().getEmail() + " | " + transaction);
                    } else if((account.getCustomer() instanceof PersonalAccount && account.getBalance() < 0) || (account.getCustomer() instanceof PersonalAccount && transaction.getTranxAmount() > 500))
                    {
                        Log.getLogger().write( index + ".    PersonalAccount");
                        Log.getLogger().write( "      Sending email to => " + account.getCustomer().getEmail() + " | " + transaction);
                        Log.getLogger().write( account.getBalance() < 0 ? "      ❌ Negative BALANCE ❌" : "");
                    }

                    if(it.hasNext()) {
                        Log.getLogger().write("|       ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿       |");
                    }

                    index++;
                }
            }
        }

        if(emailHeaderAdded) {
            Log.getLogger().write("⎩＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ SENT ALL EMAILS ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿⎭");
        }
        accountService.clearChangedAccountList();
    }
}
