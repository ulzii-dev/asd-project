package backend.banking.commands;

import backend.banking.service.BankAccountService;
import ui.UIControl;

public class AddCompanyAccountCommand implements Command{
    public void execute(UIControl control) {
        BankAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }
}
