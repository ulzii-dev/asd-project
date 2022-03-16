package backend.banking.commands;

import backend.banking.service.BankingAccountService;
import ui.UIControl;

public class AddCompanyAccountCommand implements Command{
    public void execute(UIControl control) {
        BankingAccountService.getInstance()
                .createAccount(
                        control.getAccountNumber(),
                        control.getCustomer(),
                        control.getAccountType());
    }
}
