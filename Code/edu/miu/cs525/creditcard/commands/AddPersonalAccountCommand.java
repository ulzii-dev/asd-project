package edu.miu.cs525.creditcard.commands;

import edu.miu.cs525.shared.dto.AccountDTO;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.creditcard.service.CreditCardAccountServiceImpl;
import edu.miu.cs525.framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        AccountDTO accountDTO = AccountDTO.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();
        CreditCardAccountServiceImpl.getInstance()
                .createAccount(accountDTO);
    }
}
