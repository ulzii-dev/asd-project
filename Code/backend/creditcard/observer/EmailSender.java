package edu.mum.cs.cs525.labs.exercises.project.backend.creditcard.observer;

import edu.mum.cs.cs525.labs.exercises.project.framework.AccountServiceBak;
import edu.mum.cs.cs525.labs.exercises.project.framework.Observer;

public class EmailSender implements Observer {
    private AccountServiceBak accountService;

    public EmailSender(AccountServiceBak accountService) {
        this.accountService = accountService;
    }

    @Override
    public void update() {

    }
}
