package edu.miu.cs525.framework.ui;

import edu.miu.cs525.commons.Customer;
import edu.miu.cs525.framework.Command;

public interface UIControl {
    void init(String title, UIConfig config);

    void setAddPersonalAccountCommand(Command addAccountCommand);

    void setAddCompanyAccountCommand(Command addAccountCommand);

    void setReportCommand(Command reportCommand);

    void setAddInterestCommand(Command addInterestCommand);

    void setDepositCommand(Command depositCommand);

    void setWithdrawCommand(Command withdrawCommand);

    void setVisible(boolean value);

    String getAccountType();

    String getAccountNumber();

    Customer getCustomer();

    String getAmount();
}
