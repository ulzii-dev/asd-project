package edu.miu.cs525.banking;

import edu.miu.cs525.banking.commands.*;
import edu.miu.cs525.framework.ui.pages.UIFrame;
import edu.miu.cs525.framework.ui.UIControl;

import javax.swing.*;

public class BankingApplicationStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl uiControl = UIFrame.getInstance();
            uiControl.init("MIU Banking Application", new BankingUIConfig());

            //Commands
            uiControl.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            uiControl.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            uiControl.setDepositCommand(new DepositCommand());
            uiControl.setWithdrawCommand(new WithdrawCommand());
            uiControl.setAddInterestCommand(new AddInterestCommand());
            //Create a new instance of our application's frame, and make it visible.
            uiControl.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
