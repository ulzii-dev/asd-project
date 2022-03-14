package edu.mum.cs.cs525.labs.exercises.project.backend.creditcard.observer;

import edu.mum.cs.cs525.labs.exercises.project.framework.AccountService;
import edu.mum.cs.cs525.labs.exercises.project.framework.Observer;

public class EmailSender implements Observer {
    private AccountService actsvc;

    public EmailSender(AccountService accountService) {
        this.actsvc = accountService;
    }

    @Override
    public void update() {

    }
}
