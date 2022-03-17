package backend.banking.commands;

import backend.banking.service.BankingAccountService;
import backend.commons.Log;
import framework.Command;
import framework.ui.UIControl;

public class ReportCommand implements Command {
    @Override
    public void execute(UIControl control) {
        Log.getLogger().write("Generating report!");
        BankingAccountService.generateReport(control.getAccountNumber());
    }
}
