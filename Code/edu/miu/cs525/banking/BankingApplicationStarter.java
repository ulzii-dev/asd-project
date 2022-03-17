package edu.miu.cs525.banking;

import edu.miu.cs525.banking.commands.*;
import edu.miu.cs525.framework.ui.pages.UIFrame;
import edu.miu.cs525.framework.ui.UIControl;

import javax.swing.*;

public class BankingApplicationStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl uiFrame = UIFrame.getInstance();
            uiFrame.init("MIU Banking Application", new BankingUIConfig());

            //Commands
            uiFrame.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            uiFrame.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            uiFrame.setDepositCommand(new DepositCommand());
            uiFrame.setWithdrawCommand(new WithdrawCommand());
            uiFrame.setAddInterestCommand(new AddInterestCommand());
            //Create a new instance of our application's frame, and make it visible.
            uiFrame.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
