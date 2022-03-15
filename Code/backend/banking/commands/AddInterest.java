package edu.mum.cs.cs525.labs.exercises.project.backend.banking.commands;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.BankAccountServiceBak;

public class AddInterest implements Command{
    @Override
    public void execute() {
        BankAccountServiceBak.getInstance().addInterest();
    }
}
