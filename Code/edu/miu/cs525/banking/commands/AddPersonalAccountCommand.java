package edu.miu.cs525.banking.commands;

import edu.miu.cs525.creditcard.utils.AccountDTOCreator;
import edu.miu.cs525.shared.dto.AccountDTO;
import edu.miu.cs525.banking.service.BankAccountServiceImpl;
import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        AccountDTO accountDTO = AccountDTOCreator.create(control);
        BankAccountServiceImpl.getInstance()
                .createAccount(accountDTO);
    }
}
