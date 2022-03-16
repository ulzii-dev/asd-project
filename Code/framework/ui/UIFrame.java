package framework.ui;


import backend.banking.commands.NoCommand;
import backend.commons.Account;
import backend.commons.AccountService;
import backend.commons.Customer;
import backend.commons.Log;
import framework.AccountOperationConstant;
import framework.Command;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A basic JFC based application.
 */
public class UIFrame extends FormTemplate implements UIControl, framework.Observer {
    private static volatile UIFrame uiFrame;
    private final ActionListener exit = (ActionListener) -> {
        System.exit(0);
    };
    protected AccountOperationConstant accountOperationCategory;
    JPanel JPanel1 = new JPanel();
    String accountnr;
    String clientName;
    String street;
    String city;
    String zip;
    String state;
    String amount;
    boolean newAccount;
    /****
     * init variables in the object
     ****/
    private Command addPersonalAccountCommand;
    private final ActionListener addPersonalAccountActionListener = (ActionListener) -> {
        openDialog(new AddPersonalAccount(uiFrame));
        if (newAccount) {
            this.addPersonalAccountCommand.execute(this);
        }
    };
    private Command addCompanyAccountCommand;
    private final ActionListener addCompanyAccountActionListener = (ActionListener) -> {
        openDialog(new AddCompanyAccount(uiFrame));
        if (newAccount) {
            this.addCompanyAccountCommand.execute(this);
        }
    };
    private Command addInterestCommand;
    private final ActionListener addInterestActionListener = (ActionListener) -> {
        this.addInterestCommand.execute(this);
        JOptionPane.showMessageDialog(null, "Add interest to all accounts", "Add interest to all accounts", JOptionPane.WARNING_MESSAGE);
    };
    private Command depositCommand;
    private Command withdrawCommand;
    private Command reportCommand;
    private Collection<String> accountTypes;
    private Customer customer;
    private String accountNumber;
    private String accountType;
    private AccountService subject;
    private UIConfig uiConfig;
    private final ActionListener depositActionListener = (ActionListener) -> {
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, uiConfig.getIdColumnIndex());
            openDialog(new Deposit(uiFrame, accnr));
            if (Integer.parseInt(this.amount) > 0) {
                this.depositCommand.execute(this);
            }
        } else {
            Log.getLogger().write("Need to select row to DEPOSIT!");
        }
    };
    private final ActionListener withdrawActionListener = (ActionListener) -> {
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, uiConfig.getIdColumnIndex());
            openDialog(new Withdraw(uiFrame, accnr));
            if (Integer.parseInt(this.amount) > 0) {
                this.withdrawCommand.execute(this);
            }
        } else {
            Log.getLogger().write("Need to select row to WITHDRAW!");
        }
    };

    private UIFrame() {
        this.addPersonalAccountCommand = new NoCommand();
        this.addCompanyAccountCommand = new NoCommand();
        this.addInterestCommand = new NoCommand();
        this.depositCommand = new NoCommand();
        this.withdrawCommand = new NoCommand();
        this.reportCommand = new NoCommand();
        this.accountTypes = new ArrayList<>();
    }

    public static UIFrame getInstance() {
        if (uiFrame == null) {
            synchronized (AccountService.class) {
                if (uiFrame == null) {
                    uiFrame = new UIFrame();
                }
            }
        }
        return uiFrame;
    }

    public void init(String title, UIConfig uiConfig) {
        Map<String, ActionListener> buttons = new LinkedHashMap<>();
        buttons.put("Add Personal Account", addPersonalAccountActionListener);
        buttons.put("Add Company Account", addCompanyAccountActionListener);
        buttons.put("Deposit", depositActionListener);
        buttons.put("Withdraw", withdrawActionListener);
        buttons.put("Add Interest", addInterestActionListener);

        buttons.put("Exit", exit);
        this.uiConfig = uiConfig;
        this.accountTypes = this.uiConfig.getAccountTypes();
        generateFormTemplate(title, uiConfig, buttons);
    }

    public String getAmount() {
        return amount;
    }

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    @Override
    public void setAddPersonalAccountCommand(Command addAccountCommand) {
        this.addPersonalAccountCommand = addAccountCommand;
    }

    @Override
    public void setAddCompanyAccountCommand(Command addAccountCommand) {
        this.addCompanyAccountCommand = addAccountCommand;
    }

    @Override
    public void setReportCommand(Command reportCommand) {
        this.reportCommand = reportCommand;
    }

    @Override
    public void setAddInterestCommand(Command addInterestCommand) {
        this.addInterestCommand = addInterestCommand;
    }

    @Override
    public void setDepositCommand(Command depositCommand) {
        this.depositCommand = depositCommand;
    }

    @Override
    public void setWithdrawCommand(Command withdrawCommand) {
        this.withdrawCommand = withdrawCommand;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Collection<String> getAccountTypes() {
        return accountTypes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    protected void notCommon() {
        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
    }

    @Override
    protected void setBtnBounds(JButton btn, int y) {
        btn.setBounds(468, y, 192, 33);
    }

    @Override
    protected void panelBounds() {
        JPanel1.setBounds(0, 0, 700, 410);
    }

    @Override
    protected void pSetSize() {
        setSize(700, 410);
    }

    @Override
    protected void tableBounds() {
        JTable1.setBounds(0, 0, 420, 0);
    }

    @Override
    protected void scrollPanelBounds() {
        JScrollPane1.setBounds(12, 24, 444, 190);
    }

    @Override
    public void update() {
        if (this.subject.getAccountOperationConstant() == AccountOperationConstant.REPORT) {
            return;
        }
        // reload accounts to view
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        this.subject.getAllAccounts().forEach(this::tableRow);
        Log.getLogger().write("Updating the UIFrame dataset!!");
    }

    public AccountService getSubject() {
        return subject;
    }

    public void setSubject(AccountService subject) {
        this.subject = subject;
    }

    void MainFrm_windowClosing(WindowEvent event) {
        // to do: code goes here.
        MainFrm_windowClosing_Interaction1(event);
    }

    void MainFrm_windowClosing_Interaction1(WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    private void tableRow(Account act) {
        model.addRow(this.uiConfig.buildRow(act));
        JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
        newAccount = false;
    }

    public void openDialog(JDialog jDialog) {
        String formName = jDialog.getClass().getSimpleName();
        if (formName.startsWith("Deposit") || formName.startsWith("With")) {
            openDialog(jDialog, 450, 20, 300, 175);
        } else {
            openDialog(jDialog, 450, 20, 300, 430);
        }
    }

    public void openDialog(JDialog jDialog, int x, int y, int width, int height) {
        jDialog.setBounds(x, y, width, height);
        jDialog.show();
    }

    public AccountOperationConstant getAccountOperationCategory() {
        return accountOperationCategory;
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent event) {
            Object object = event.getSource();
            if (object == UIFrame.this)
                MainFrm_windowClosing(event);
        }
    }
}
