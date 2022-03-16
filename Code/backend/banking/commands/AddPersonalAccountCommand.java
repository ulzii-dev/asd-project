package backend.banking.commands;

import backend.banking.service.BankAccountService;
import backend.commons.AccountService;
import ui.UIControl;

public class AddPersonalAccountCommand implements Command {
    @Override
    public void execute(UIControl control) {
        BankAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }

}
