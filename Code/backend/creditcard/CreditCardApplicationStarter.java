package backend.creditcard;

import backend.creditcard.commands.DepositCommand;
import backend.creditcard.commands.WithdrawCommand;
import backend.creditcard.commands.AddCompanyAccountCommand;
import backend.creditcard.commands.AddPersonalAccountCommand;
import framework.ui.UIFrame;
import framework.ui.UIControl;

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
            //Create a new instance of our application's frame, and make it visible.
            uIControl.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
