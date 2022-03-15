package backend.creditcard.observer;

import backend.commons.AccountService;
import framework.AccountServiceBak;
import framework.Observer;

public class EmailSender implements Observer {
    private final AccountService accountService;

    public EmailSender(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {

    }
}
