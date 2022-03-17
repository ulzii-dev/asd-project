package edu.miu.cs525.banking.commands;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.banking.service.BankAccountCreator;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class AddCompanyAccountCommand implements Command {
    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();
        BankAccountCreator.getInstance()
                .createAccount(accountData);
    }
}
