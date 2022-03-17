package edu.miu.cs525.banking.commands;

import edu.miu.cs525.banking.service.BankAccountServiceImpl;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class WithdrawCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {
        BankAccountServiceImpl.getInstance().withdraw(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
