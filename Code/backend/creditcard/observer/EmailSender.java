package backend.creditcard.observer;

import framework.AccountServiceBak;
import framework.Observer;

public class EmailSender implements Observer {
    private AccountServiceBak accountService;

    public EmailSender(AccountServiceBak accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {

    }
}
