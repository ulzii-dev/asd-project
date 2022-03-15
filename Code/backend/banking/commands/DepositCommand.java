package backend.banking.commands;

import backend.commons.BankAccountService;

public class DepositCommand implements Command {
    @Override
    public void execute() {
        BankAccountService.getInstance().deposit("", 0d);
    }
}
