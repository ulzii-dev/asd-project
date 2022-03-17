package backend.banking.commands;

import backend.banking.builder.AccountData;
import backend.banking.dto.AccountDTO;
import backend.banking.service.BankingAccountService;
import framework.Command;
import framework.ui.UIControl;

public class AddCompanyAccountCommand implements Command {
    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();
        BankingAccountService.getInstance()
                .createAccount(accountData);
    }
}
