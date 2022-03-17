package edu.miu.cs525.banking.commands;

import edu.miu.cs525.banking.service.BankingAccountService;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class DepositCommand implements Command {
    @Override
    public void execute(UIControl uiControl) {
        BankingAccountService.getInstance().deposit(uiControl.getAccountNumber(), Double.parseDouble(uiControl.getAmount()));
    }
}
