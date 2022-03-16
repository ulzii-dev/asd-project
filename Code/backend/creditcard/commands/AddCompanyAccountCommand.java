package backend.creditcard.commands;

import backend.creditcard.service.CreditCardAccountService;
import framework.Command;
import framework.ui.UIControl;

public class AddCompanyAccountCommand implements Command {
    public void execute(UIControl control) {
        CreditCardAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }
}
