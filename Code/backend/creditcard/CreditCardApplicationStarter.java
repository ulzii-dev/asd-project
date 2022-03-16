package backend.creditcard;

import backend.creditcard.commands.AddCompanyAccountCommand;
import backend.creditcard.commands.AddPersonalAccountCommand;
import framework.ui.UIFrame;
import ui.UIControl;

import javax.swing.*;

public class CreditCardApplicationStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl mainForm = UIFrame.getInstance();
            mainForm.init("MIU CreditCard Application", new CreditCardUIConfig());

            //commands
            mainForm.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            mainForm.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            //Create a new instance of our application's frame, and make it visible.
            mainForm.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
