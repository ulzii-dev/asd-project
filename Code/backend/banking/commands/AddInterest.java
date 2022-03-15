package edu.mum.cs.cs525.labs.exercises.project.backend.banking.commands;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.BankAccountService;

public class AddInterest implements Command{
    @Override
    public void execute() {
        BankAccountService.getInstance().addInterest();
    }
}
