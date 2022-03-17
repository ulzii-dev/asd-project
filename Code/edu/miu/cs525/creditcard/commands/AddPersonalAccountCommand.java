package edu.miu.cs525.creditcard.commands;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.creditcard.service.CreditCardAccountCreator;
import edu.miu.cs525.framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();
        CreditCardAccountCreator.getInstance()
                .createAccount(accountData);
    }
}
