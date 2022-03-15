package backend.banking.commands;

import backend.commons.BankAccountService;

public class DepositCommand implements Command {
    private String accountNumber;
    private double amount;

    public DepositCommand(String accNumber, double amount) {
        this.accountNumber = accNumber;
        this.amount = amount;
    }

    @Override
    public void execute() {
        BankAccountService.getInstance().deposit(this.accountNumber, this.amount);
    }
}
