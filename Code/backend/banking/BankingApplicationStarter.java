package backend.banking;

import backend.banking.commands.AddCompanyAccountCommand;
import backend.banking.commands.AddPersonalAccountCommand;
import framework.ui.UIFrame;
import framework.ui.UIControl;

import javax.swing.*;

public class BankingApplicationStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl uIControl = UIFrame.getInstance();
            uIControl.init("MIU Banking Application", new BankingUIConfig());

            //commands
            uIControl.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            uIControl.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            //Create a new instance of our application's frame, and make it visible.
            uIControl.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
