package backend.creditcard.commands;

import backend.banking.builder.AccountData;
import backend.banking.dto.AccountDTO;
import framework.Command;
import backend.creditcard.service.CreditCardAccountService;
import framework.ui.UIControl;

public class AddCompanyAccountCommand implements Command {

    public void execute(UIControl control) {
        AccountData accountData = AccountData.builder()
                .accountNumber(control.getAccountNumber())
                .accountType(control.getAccountType())
                .customer(control.getCustomer()).build();
        CreditCardAccountService.getInstance()
                .createAccount(accountData);
    }
}
