package backend.banking.commands;

import backend.banking.builder.AccountData;
import backend.banking.dto.AccountDTO;
import backend.banking.service.BankingAccountService;
import backend.commons.Account;
import framework.Command;
import framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();

        BankingAccountService.getInstance()
                .createAccount(accountData);
    }
}
