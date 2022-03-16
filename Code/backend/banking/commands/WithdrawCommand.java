package backend.banking.commands;

import backend.banking.service.BankingAccountService;
import framework.Command;
import framework.ui.UIControl;

public class WithdrawCommand implements Command {
    @Override
    public void execute(UIControl uiControl){
        BankingAccountService.getInstance().withdraw(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
