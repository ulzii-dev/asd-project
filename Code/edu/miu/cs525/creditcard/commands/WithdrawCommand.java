package edu.miu.cs525.creditcard.commands;

import edu.miu.cs525.creditcard.service.CreditCardAccountServiceImpl;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class WithdrawCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {
        CreditCardAccountServiceImpl.getInstance().withdraw(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
