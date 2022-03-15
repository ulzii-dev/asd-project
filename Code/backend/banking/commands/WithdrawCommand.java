package backend.banking.commands;

import backend.commons.BankAccountServiceBak;

public class WithdrawCommand implements Command {
    @Override
    public void execute() {
        BankAccountServiceBak.getInstance().withdraw("", 0d);
    }
}
