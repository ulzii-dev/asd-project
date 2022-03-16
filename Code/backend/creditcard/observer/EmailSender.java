package backend.creditcard.observer;

import backend.commons.*;
import framework.Observer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailSender implements Observer {
    private final AccountService accountService;

    public EmailSender(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {
        Log.getLogger().write("Pulling data from SUBJECT");
        System.out.println(accountService.getAccountTransactions());

        for (Map.Entry<Account, ArrayList<AccountTransaction>> entry : accountService.getAccountTransactions().entrySet()) {
            Account account = entry.getKey();
            List<AccountTransaction> transactions = entry.getValue();
            if(account.getCustomer() instanceof Company || (account.getCustomer() instanceof Personal)) {
                for(AccountTransaction transaction : transactions) {
                    Log.getLogger().write("Sending email to :" + account.getCustomer().getEmail() + " | " + transaction);
                }
            }
        }
        accountService.clearChangedAccountList();
    }
}
