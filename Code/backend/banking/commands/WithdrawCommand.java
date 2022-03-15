package backend.banking.commands;

import backend.commons.BankAccountService;

public class WithdrawCommand implements Command {
    private String accountNumber;
    private double amount;

    public WithdrawCommand(String accNumber, double amount) {
        this.accountNumber = accNumber;
        this.amount = amount;
    }

    @Override
    public void execute() {
        BankAccountService.getInstance().withdraw(this.accountNumber, this.amount);
    }
}
