package edu.miu.cs525.creditcard;

import edu.miu.cs525.framework.ui.pages.UIFrame;
import edu.miu.cs525.framework.ui.UIControl;
import edu.miu.cs525.creditcard.commands.*;

import javax.swing.*;

public class CreditCardApplicationStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl uIControl = UIFrame.getInstance();
            uIControl.init("MIU CreditCard Application", new CreditCardUIConfig());

            //commands
            uIControl.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            uIControl.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            uIControl.setDepositCommand(new DepositCommand());
            uIControl.setWithdrawCommand(new WithdrawCommand());
            uIControl.setAddInterestCommand(new AddInterestCommand());
            uIControl.setReportCommand(new ReportCommand());

            //Create a new instance of our application's frame, and make it visible.
            uIControl.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
