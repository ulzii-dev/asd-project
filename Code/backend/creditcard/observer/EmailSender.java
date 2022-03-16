package backend.creditcard.observer;

import backend.commons.*;
import framework.Observer;

public class EmailSender implements Observer {
    private final AccountService accountService;

    public EmailSender(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {
        for (Account account : accountService.getAllAccounts()) {
            if(account.getCustomer() instanceof Company || (account.getCustomer() instanceof Personal)) {
                Log.getLogger().write("Sending email to :" + account.getCustomer().getEmail());
                accountService.clearChangedAccountList();
            }
        }
    }
}
