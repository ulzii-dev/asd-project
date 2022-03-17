package backend.creditcard.commands;

import backend.creditcard.service.CreditCardAccountService;
import framework.Command;
import framework.ui.UIControl;

public class WithdrawCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {
        CreditCardAccountService.getInstance().withdraw(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
