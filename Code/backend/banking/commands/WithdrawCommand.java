package backend.banking.commands;

import backend.commons.BankAccountService;

public class WithdrawCommand implements Command {
    @Override
    public void execute() {
        BankAccountService.getInstance().withdraw("", 0d);
    }
}
