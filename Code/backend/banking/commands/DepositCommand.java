package backend.banking.commands;

import backend.commons.BankAccountServiceBak;

public class DepositCommand implements Command {
    @Override
    public void execute() {
        BankAccountServiceBak.getInstance().deposit("", 0d);
    }
}
