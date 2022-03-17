package edu.miu.cs525.framework.ui.pages;

import javax.swing.*;
import java.awt.*;

public class GenerateReport extends javax.swing.JDialog {
    protected JTextArea report;
    public GenerateReport(Frame parent) {
        super(parent);

        report = new JTextArea();

        getContentPane().setLayout(null);
        setSize(780,560);
        setVisible(false);
        getContentPane().add(JScrollPane1);
        JScrollPane1.setBounds(24,24,780,560);
        JScrollPane1.getViewport().add(report);
        report.setBounds(0,0,355,237);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(360,590,96,24);

        //{{REGISTER_LISTENERS
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        //JTextArea.setText(BankingAccountService.report);
        //}}
    }

    public void setReport(String reportContent){
        report.setText(reportContent);
    }

    public GenerateReport()
    {
        this((Frame)null);
    }

    //{{DECLARE_CONTROLS
    javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();
    javax.swing.JTextArea JTextArea = new javax.swing.JTextArea();
    javax.swing.JButton JButton_OK = new javax.swing.JButton();
    //}}

    class SymAction implements java.awt.event.ActionListener
    {
        public void actionPerformed(java.awt.event.ActionEvent event)
        {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
        }
    }


    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event)
    {
        dispose();
    }
}

