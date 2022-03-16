package backend.creditcard.commands;

import framework.Command;
import backend.creditcard.service.CreditCardAccountService;
import framework.ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        CreditCardAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }
}
