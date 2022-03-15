package edu.mum.cs.cs525.labs.exercises.project.backend.banking.commands;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.BankAccountService;

public class DepositCommand implements Command {
    @Override
    public void execute() {
        BankAccountService.getInstance().deposit("", 0d);
    }
}
