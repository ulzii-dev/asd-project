package edu.miu.cs525.banking.commands;

import edu.miu.cs525.shared.builder.AccountData;
import edu.miu.cs525.banking.service.BankAccountServiceImpl;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();

        BankAccountServiceImpl.getInstance()
                .createAccount(accountData);
    }
}
