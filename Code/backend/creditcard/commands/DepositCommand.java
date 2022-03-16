package backend.creditcard.commands;

import backend.creditcard.service.CreditCardAccountService;
import framework.Command;
import framework.ui.UIControl;

public class DepositCommand implements Command {
    @Override
    public void execute(UIControl uiControl){
        CreditCardAccountService.getInstance().deposit(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
