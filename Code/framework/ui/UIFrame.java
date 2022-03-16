package framework.ui;


import backend.commons.Log;
import backend.banking.commands.NoCommand;
import backend.commons.Account;
import backend.commons.AccountService;
import backend.commons.Customer;
import com.sun.source.tree.Tree;
import framework.AccountOperationConstant;
import framework.Command;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * A basic JFC based application.
 */
public class UIFrame extends FormTemplate implements UIControl, framework.Observer
{
	/****
	 * init variables in the object
	 ****/
	private Command addPersonalAccountCommand;
	private Command addCompanyAccountCommand;
	private Command addInterestCommand;
	private Command depositCommand;
	private Command withdrawCommand;
	private Command reportCommand;

	protected AccountOperationConstant accountOperationCategory;

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
	private static volatile UIFrame uiFrame;


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
		Map<String,ActionListener> buttons = new LinkedHashMap<>();
		buttons.put("Add Personal Account", addPersonalAccountActionListener);
		buttons.put("Add Company Account", addCompanyAccountActionListener);
		buttons.put("Deposit", depositActionListener);
		buttons.put("Withdraw", withdrawActionListener);
		buttons.put("Add Interest", addInterestActionListener);

		buttons.put("Exit",exit);
		this.uiConfig = uiConfig;
		this.accountTypes = this.uiConfig.getAccountTypes();
		generateFormTemplate(title,uiConfig,buttons);
	}

	public String getAmount() {
		return amount;
	}

	private final ActionListener exit = (ActionListener) -> {
		System.exit(0);
	};

	private final ActionListener addPersonalAccountActionListener = (ActionListener) -> {
		openDialog(new AddPersonalAccount(uiFrame));
		if (newAccount) {
			this.addPersonalAccountCommand.execute(this);
		}
	};

	private final ActionListener addCompanyAccountActionListener = (ActionListener) -> {
		openDialog(new AddCompanyAccount(uiFrame));
		if (newAccount) {
			this.addCompanyAccountCommand.execute(this);
		}
	};

	private final ActionListener addInterestActionListener = (ActionListener) -> {
		this.addInterestCommand.execute(this);
		JOptionPane.showMessageDialog(null, "Added interest to all accounts", "Added interest to all accounts", JOptionPane.WARNING_MESSAGE);
	};

	private final ActionListener depositActionListener = (ActionListener) -> {
	int selection = JTable1.getSelectionModel().getSelectedIndices()[0];
	if (selection >= 0) {
		String accnr = (String) model.getValueAt(selection, uiConfig.getIdColumnIndex());
		openDialog(new Deposit(uiFrame, accnr));
		this.depositCommand.execute(this);
	} else {
		Log.getLogger().write("Need to select row to DEPOSIT!");
	}
};

	private final ActionListener withdrawActionListener = (ActionListener) -> {
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, uiConfig.getIdColumnIndex());
			openDialog(new Withdraw(uiFrame, accnr));
			this.withdrawCommand.execute(this);
		} else {
			Log.getLogger().write("Need to select row to WITHDRAW!");
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
			if (object == UIFrame.this)
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

	public AccountOperationConstant getAccountOperationCategory() {
		return accountOperationCategory;
	}
}
