package edu.miu.cs525.banking.commands;

import edu.miu.cs525.framework.Command;
import edu.miu.cs525.framework.ui.UIControl;
import edu.miu.cs525.banking.service.BankingAccountService;

public class ReportCommand implements Command {
    @Override
    public void execute(UIControl control) {
        BankingAccountService.generateReport(control.getAccountNumber(), control.getReportUI());
    }
}
