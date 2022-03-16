package ui;

import backend.banking.BankingUIConfig;
import backend.banking.commands.AddCompanyAccountCommand;
import backend.banking.commands.AddPersonalAccountCommand;
import ui.bank.MainForm;

import javax.swing.*;

public class BankUIStarter {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIControl frm = MainForm.getInstance();
            frm.init("Banking Application", new BankingUIConfig());

            //commands
            frm.setAddPersonalAccountCommand(new AddPersonalAccountCommand());
            frm.setAddCompanyAccountCommand(new AddCompanyAccountCommand());
            //Create a new instance of our application's frame, and make it visible.
            frm.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
}
