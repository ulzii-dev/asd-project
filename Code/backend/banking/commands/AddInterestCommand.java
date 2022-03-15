package backend.banking.commands;

import backend.commons.BankAccountService;

public class AddInterestCommand implements Command{
    @Override
    public void execute() {
        BankAccountService.getInstance().addInterest();
    }
}
