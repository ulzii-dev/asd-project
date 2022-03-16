package framework;


import backend.commons.Log;
import backend.creditcard.service.CreditCardAccountService;
import backend.banking.commands.NoCommand;
import backend.commons.Account;
import backend.commons.AccountService;
import backend.commons.Customer;
import framework.ui.AddCompanyAccount;
import framework.ui.AddPersonalAccount;
import ui.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * A basic JFC based application.
 */
public class ApplicationContext extends FormTemplate implements UIControl, framework.Observer
{
    /****
     * init variables in the object
     ****/
	private Command addPersonalAccountCommand;
	private Command addCompanyAccountCommand;

	protected AccountOperationCategory accountOperationCategory;

	private Collection<String> accountTypes;
	private Customer customer;
	private String accountNumber;
	private String accountType;

	JPanel JPanel1 = new JPanel();

	String accountnr;
	String clientName;
	String street;
	String city;
	String zip;
	String state;
	String amount;
    boolean newAccount;

    private AccountService subject;
    private UIConfig uiConfig;
    private static volatile ApplicationContext applicationContext;


	private ApplicationContext() {
		this.addPersonalAccountCommand = new NoCommand();
		this.addCompanyAccountCommand = new NoCommand();
		this.accountTypes = new ArrayList<>();
	}

	public static ApplicationContext getInstance() {
		if (applicationContext == null) {
			synchronized (CreditCardAccountService.class) {
				if (applicationContext == null) {
					applicationContext = new ApplicationContext();
				}
			}
		}
		return applicationContext;
	}

	public void init(String title, UIConfig uiConfig) {
		Map<String,ActionListener> buttons = new HashMap<>();
		buttons.put("Add personal account", addPersonalAccountActionListener);
		buttons.put("Add company account", addCompanyAccountActionListener);
		buttons.put("Exit",exit);
		this.uiConfig = uiConfig;
		this.accountTypes = this.uiConfig.getAccountTypes();
		generateForm(title,uiConfig,buttons);
	}

	public String getAmount() {
		return amount;
	}

	private final ActionListener exit = (ActionListener) -> {
		System.exit(0);
	};

	private final ActionListener addPersonalAccountActionListener = (ActionListener) -> {
		openDialog(new AddPersonalAccount(applicationContext));
		if (newAccount) {
			this.addPersonalAccountCommand.execute(this);
		}
	};

	private final ActionListener addCompanyAccountActionListener = (ActionListener) -> {
		openDialog(new AddCompanyAccount(applicationContext));
		if (newAccount) {
			this.addCompanyAccountCommand.execute(this);
		}
	};

	void exitApplication(){
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

	}

	@Override
	public void setAddInterestCommand(Command addInterestCommand) {

	}

	@Override
	public void setDepositCommand(Command depositCommand) {

	}

	@Override
	public void setWithdrawCommand(Command withdrawCommand) {
	}

	@Override
	public String getAccountType() {
		return accountType;
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

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	protected void notCommon() {
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
	}

	@Override
	protected void setBtnBounds(JButton btn, int y) {
		btn.setBounds(468,y,192,33);
	}

	@Override
	protected void panelBounds() {
		JPanel1.setBounds(0,0,700,410);
	}

	@Override
	protected void pSetSize() {
		setSize(700,410);
	}

	@Override
	protected void tableBounds() {
		JTable1.setBounds(0, 0, 420, 0);
	}

	@Override
	protected void scrollPanelBounds() {
		JScrollPane1.setBounds(12,24,444,190);
	}

	@Override
	public void update() {
		if (this.subject.getAccountOperationCategory() == AccountOperationCategory.REPORT) {
			return;
		}
		// reload accounts to view
		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i > -1; i--) {
				model.removeRow(i);
			}
		}
		this.subject.getAllAccounts().forEach(this::tableRow);
		Log.instance.write("Update table in the MainForm");
	}

	public void setSubject(AccountService subject) {
		this.subject = subject;
	}

	public AccountService getSubject() {
		return subject;
	}


	class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			Object object = event.getSource();
			if (object == ApplicationContext.this)
				MainFrm_windowClosing(event);
		}
	}

	void MainFrm_windowClosing(WindowEvent event)
	{
		// to do: code goes here.
		MainFrm_windowClosing_Interaction1(event);
	}

	void MainFrm_windowClosing_Interaction1(WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	private void tableRow(Account act){
		model.addRow(this.uiConfig.buildRow(act));
		JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
		newAccount = false;
	}

	public void openDialog(JDialog jDialog){
		openDialog(jDialog, 450, 20, 300, 430);
	}
	public void openDialog(JDialog jDialog, int x, int y, int width, int height){
		jDialog.setBounds(x, y, width, height);
		jDialog.show();
	}

	public AccountOperationCategory getAccountOperationCategory() {
		return accountOperationCategory;
	}
}
