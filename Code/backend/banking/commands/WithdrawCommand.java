package backend.banking.commands;

import backend.banking.service.BankingAccountService;
import framework.Command;
import framework.ui.UIControl;

public class WithdrawCommand implements Command {
//    private String accountNumber;
//    private double amount;
//
//    public WithdrawCommand(String accNumber, double amount) {
//        this.accountNumber = accNumber;
//        this.amount = amount;
//    }

    @Override
    public void execute(UIControl uiControl){
        BankingAccountService.getInstance().withdraw(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
