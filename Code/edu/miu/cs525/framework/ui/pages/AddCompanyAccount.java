package edu.miu.cs525.framework.ui.pages;

import edu.miu.cs525.framework.domain.CompanyAccount;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AddCompanyAccount extends javax.swing.JDialog {
    private final UIFrame parentframe;
    List<JRadioButton> btnAccountTyps = new ArrayList<>();

    //{{DECLARE_CONTROLS
    JRadioButton JRadioButton_Chk = new JRadioButton();
    JRadioButton JRadioButton_Sav = new JRadioButton();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JLabel JLabel6 = new JLabel();
    JLabel JLabel7 = new JLabel();
    JTextField JTextField_NAME = new JTextField();
    JTextField JTextField_CITY = new JTextField();
    JTextField JTextField_STATE = new JTextField();
    JTextField JTextField_STREET = new JTextField();
    JTextField JTextField_ZIP = new JTextField();
    JTextField JTextField_NoOfEmp = new JTextField();
    JTextField JTextField_EMAIL = new JTextField();
    JButton JButton_OK = new JButton();
    JButton JButton_CANCEL = new JButton();
    JLabel JLabel8 = new JLabel();
    JTextField JTextField_ACCOUNT_NUMBER = new JTextField();

    public AddCompanyAccount(UIFrame parent) {
        super(parent);
        parentframe = parent;

        setTitle("Add Company account");
        setModal(true);
        getContentPane().setLayout(null);
        setVisible(false);
        setSize(283, 303);
        int yOffset = 0;
        SymMouse aSymMouse = new SymMouse();

        for (String actType : parentframe.getAccountTypes()) {
            // Build account types radio buttons
            JRadioButton chk = new JRadioButton();
            chk.setText(actType);
            chk.setActionCommand(actType);
            chk.setBounds(36, yOffset, 200, 24);
            getContentPane().add(chk);
            chk.addMouseListener(aSymMouse);
            btnAccountTyps.add(chk);
            yOffset += 24;
        }
        JLabel8.setText("Acc Nr");
        getContentPane().add(JLabel8);
        JLabel8.setForeground(Color.black);
        JLabel8.setBounds(12, 72, 48, 24);
        getContentPane().add(JTextField_ACCOUNT_NUMBER);
        JTextField_ACCOUNT_NUMBER.setBounds(120, 72, 156, 20);
        JLabel1.setText("Name");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(Color.black);
        JLabel1.setBounds(12, 96, 48, 24);
        JLabel2.setText("Street");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(Color.black);
        JLabel2.setBounds(12, 120, 48, 24);
        JLabel3.setText("City");
        getContentPane().add(JLabel3);
        JLabel3.setForeground(Color.black);
        JLabel3.setBounds(12, 144, 48, 24);
        JLabel4.setText("State");
        getContentPane().add(JLabel4);
        JLabel4.setForeground(Color.black);
        JLabel4.setBounds(12, 168, 48, 24);
        JLabel5.setText("Zip");
        getContentPane().add(JLabel5);
        JLabel5.setForeground(Color.black);
        JLabel5.setBounds(12, 192, 48, 24);
        JLabel6.setText("No of employees");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(Color.black);
        JLabel6.setBounds(12, 216, 96, 24);
        JLabel7.setText("Email");
        getContentPane().add(JLabel7);
        JLabel7.setForeground(Color.black);
        JLabel7.setBounds(12, 240, 48, 24);
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(120, 96, 156, 20);
        getContentPane().add(JTextField_CITY);
        JTextField_CITY.setBounds(120, 144, 156, 20);
        getContentPane().add(JTextField_STATE);
        JTextField_STATE.setBounds(120, 168, 156, 20);
        getContentPane().add(JTextField_STREET);
        JTextField_STREET.setBounds(120, 120, 156, 20);
        getContentPane().add(JTextField_ZIP);
        JTextField_ZIP.setBounds(120, 192, 156, 20);
        getContentPane().add(JTextField_NoOfEmp);
        JTextField_NoOfEmp.setBounds(120, 216, 156, 20);
        getContentPane().add(JTextField_EMAIL);
        JTextField_EMAIL.setBounds(120, 240, 156, 20);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(48, 276, 84, 24);
        JButton_CANCEL.setText("Cancel");
        JButton_CANCEL.setActionCommand("Cancel");
        getContentPane().add(JButton_CANCEL);
        JButton_CANCEL.setBounds(156, 276, 84, 24);
        //}}

        //{{REGISTER_LISTENERS
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        JButton_CANCEL.addActionListener(lSymAction);
        //}}
    }

    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
        parentframe.accountnr = JTextField_ACCOUNT_NUMBER.getText();
        parentframe.clientName = JTextField_NAME.getText();
        parentframe.street = JTextField_STREET.getText();
        parentframe.city = JTextField_CITY.getText();
        parentframe.zip = JTextField_ZIP.getText();
        parentframe.state = JTextField_STATE.getText();
        CompanyAccount company = new CompanyAccount(
                ThreadLocalRandom.current().nextInt(),
                JTextField_NAME.getText(),
                JTextField_STREET.getText(),
                JTextField_CITY.getText(),
                JTextField_STATE.getText(),
                Integer.parseInt(JTextField_ZIP.getText()),
                JTextField_EMAIL.getText(),
                Integer.parseInt(JTextField_NoOfEmp.getText())
        );
        parentframe.setCustomer(company);
        btnAccountTyps.stream().filter(JRadioButton::isSelected)
                .findFirst()
                .ifPresent(jRadioButton -> parentframe.setAccountType(jRadioButton.getText()));
        parentframe.setAccountNumber(JTextField_ACCOUNT_NUMBER.getText());
        parentframe.newAccount = true;
        dispose();
    }

    void JButtonCANCEL_actionPerformed(java.awt.event.ActionEvent event) {
        dispose();
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
            else if (object == JButton_CANCEL)
                JButtonCANCEL_actionPerformed(event);
        }
    }

    class SymMouse extends java.awt.event.MouseAdapter {
        public void mouseClicked(java.awt.event.MouseEvent event) {
            JRadioButton selectedBtn = (JRadioButton) event.getSource();
            for (JRadioButton btn : btnAccountTyps) {
                btn.setSelected(btn == selectedBtn);
            }
        }
    }
}