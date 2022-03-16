package backend.creditcard.commands;

import backend.creditcard.CreditCardAccount;
import backend.creditcard.service.CreditCardAccountService;
import framework.Command;
import framework.ui.UIControl;

public class AddInterestCommand implements Command {
    @Override
    public void execute(UIControl control) {
        CreditCardAccountService.getInstance().addInterest();
    }
}
