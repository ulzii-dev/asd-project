package backend.banking.commands;

import backend.commons.BankAccountServiceBak;

public class AddInterestCommand implements Command{
    @Override
    public void execute() {
        BankAccountServiceBak.getInstance().addInterest();
    }
}
