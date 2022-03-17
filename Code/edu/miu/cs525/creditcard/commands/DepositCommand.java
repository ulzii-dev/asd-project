package edu.miu.cs525.creditcard.commands;

import edu.miu.cs525.creditcard.service.CreditCardAccountService;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class DepositCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {
        CreditCardAccountService.getInstance().deposit(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
