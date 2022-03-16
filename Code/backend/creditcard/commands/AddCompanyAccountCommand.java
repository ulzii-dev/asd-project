package backend.creditcard.commands;

import framework.Command;
import backend.creditcard.service.CreditCardAccountService;
import ui.UIControl;

public class AddCompanyAccountCommand implements Command {
    public void execute(UIControl control) {
        CreditCardAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }
}
