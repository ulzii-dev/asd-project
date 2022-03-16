package framework.ui;
/*
		A basic implementation of the JDialog class.
*/

import backend.creditcard.CreditCardType;
import framework.domain.PersonalAccount;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddPersonalAccount extends javax.swing.JDialog {

    private UIFrame parentframe;
    List<JRadioButton> btnAccountTyps = new ArrayList<>();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JLabel JLabel3 = new JLabel();
    JLabel JLabel4 = new JLabel();
    JLabel JLabel5 = new JLabel();
    JLabel JLabel6 = new JLabel();
    JLabel JLabel7 = new JLabel();
    JTextField JTextField_ACCOUNT_NUMBER = new JTextField();
    JTextField JTextField_NAME = new JTextField();
    JTextField JTextField_CITY = new JTextField();
    JTextField JTextField_STATE = new JTextField();
    JTextField JTextField_STREET = new JTextField();
    JTextField JTextField_ZIP = new JTextField();
    JTextField JTextField_BIRTH_DATE = new JTextField();
    JTextField JTextField_EMAIL = new JTextField();
    JButton JButton_OK = new JButton();
    JButton JButton_CANCEL = new JButton();
    JLabel JLabel8 = new JLabel();

    public AddPersonalAccount(UIFrame parent) {
        super(parent);
        parentframe=parent;

        //{{INIT_CONTROLS
        setTitle("Add Personal Account");
        setModal(true);
        getContentPane().setLayout(null);
        setVisible(false);
        setSize(383,503);
        int yOffset = 0;
        SymMouse aSymMouse = new SymMouse();
        for (String actType: parentframe.getAccountTypes()) {
            // Build account types radio buttons
            JRadioButton chk = new JRadioButton();
            chk.setText(actType);
            chk.setActionCommand(actType);
            chk.setBounds(36, yOffset,200,24);
            getContentPane().add(chk);
            chk.addMouseListener(aSymMouse);
            btnAccountTyps.add(chk);
            yOffset += 24;
        }
        getContentPane().add(JTextField_ACCOUNT_NUMBER);
        JTextField_ACCOUNT_NUMBER.setBounds(84,yOffset + 60,156,20);

        if(parentframe.getAccountTypes().contains(CreditCardType.GOLD.name())){
            JLabel8.setText("CC No.");
        }else{
            JLabel8.setText("Acc No.");
        }
        getContentPane().add(JLabel8);
        JLabel8.setForeground(Color.black);
        JLabel8.setBounds(12,yOffset + 60,48,24);
        JLabel1.setText("Name");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(Color.black);
        JLabel1.setBounds(12,yOffset + 84,48,24);
        JLabel2.setText("Street");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(Color.black);
        JLabel2.setBounds(12,yOffset + 108,48,24);
        JLabel3.setText("City");
        getContentPane().add(JLabel3);
        JLabel3.setForeground(Color.black);
        JLabel3.setBounds(12,yOffset + 132,48,24);
        JLabel4.setText("State");
        getContentPane().add(JLabel4);
        JLabel4.setForeground(Color.black);
        JLabel4.setBounds(12,yOffset + 156,48,24);
        JLabel5.setText("Zip");
        getContentPane().add(JLabel5);
        JLabel5.setForeground(Color.black);
        JLabel5.setBounds(12,yOffset + 180,48,24);
        JLabel6.setText("Birthdate");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(Color.black);
        JLabel6.setBounds(12,yOffset + 204,96,24);
        JLabel7.setText("Email");
        getContentPane().add(JLabel7);
        JLabel7.setForeground(Color.black);
        JLabel7.setBounds(12,yOffset + 228,48,24);
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(84,yOffset + 84,156,20);
        getContentPane().add(JTextField_CITY);
        JTextField_CITY.setBounds(84,yOffset + 132,156,20);
        getContentPane().add(JTextField_STATE);
        JTextField_STATE.setBounds(84,yOffset + 156,156,20);
        getContentPane().add(JTextField_STREET);
        JTextField_STREET.setBounds(84,yOffset + 108,156,20);
        getContentPane().add(JTextField_ZIP);
        JTextField_ZIP.setBounds(84,yOffset + 180,156,20);
        getContentPane().add(JTextField_BIRTH_DATE);
        JTextField_BIRTH_DATE.setBounds(84,yOffset + 204,156,20);
        getContentPane().add(JTextField_EMAIL);
        JTextField_EMAIL.setBounds(84,yOffset + 228,156,20);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(48,yOffset + 264,84,24);
        JButton_CANCEL.setText("Cancel");
        JButton_CANCEL.setActionCommand("Cancel");
        getContentPane().add(JButton_CANCEL);
        JButton_CANCEL.setBounds(156,yOffset + 264,84,24);
        //}}

        //{{REGISTER_LISTENERS
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        JButton_CANCEL.addActionListener(lSymAction);
        //}}
    }

    class SymMouse extends java.awt.event.MouseAdapter {
        public void mouseClicked(java.awt.event.MouseEvent event) {
            JRadioButton selectedBtn = (JRadioButton) event.getSource();
            for (JRadioButton btn: btnAccountTyps) {
                btn.setSelected(btn == selectedBtn);
            }
        }
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

    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
        PersonalAccount person = new PersonalAccount(
                1,
                JTextField_NAME.getText(),
                JTextField_STREET.getText(),
                JTextField_CITY.getText(),
                JTextField_STATE.getText(),
                Integer.parseInt(JTextField_ZIP.getText()),
                JTextField_EMAIL.getText(),
                LocalDate.parse(JTextField_BIRTH_DATE.getText())
        );
        parentframe.setCustomer(person);
        btnAccountTyps.stream().filter(JRadioButton::isSelected)
                .findFirst()
                .ifPresent(jRadioButton -> parentframe.setAccountType(jRadioButton.getText()));
        parentframe.setAccountNumber(JTextField_ACCOUNT_NUMBER.getText());
        parentframe.newAccount = true;
        dispose();
    }

    void JButtonCANCEL_actionPerformed(java.awt.event.ActionEvent event) {
        //make this frame invisible if Cancel button is clicked
        dispose();
    }
}