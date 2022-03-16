package backend.banking.commands;

import backend.banking.service.BankingAccountService;
import framework.Command;
import framework.ui.UIControl;

public class DepositCommand implements Command {
    @Override
    public void execute(UIControl uiControl){
        BankingAccountService.getInstance().deposit(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
