package edu.miu.cs525.commons;

import edu.miu.cs525.banking.service.BankingAccountService;
import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.Observable;
import edu.miu.cs525.framework.AccountOperationConstant;
import edu.miu.cs525.framework.ui.pages.GenerateReport;
import edu.miu.cs525.framework.ui.pages.UIFrame;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AccountService implements Observable {
	private final AccountDAO accountDAO;
	protected AccountOperationConstant accountOperationConstant;
	private List<Observer> observerList;
	private Map<Account, ArrayList<AccountTransaction>> updatedAccountList = new HashMap<>();

	public AccountService(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
		this.observerList = new ArrayList<>();
		this.registerObserver(UIFrame.getInstance());
		UIFrame.getInstance().setSubject(this);
	}

	public abstract Account createAccountFactory(AccountData accountData);

	public final void createAccount(AccountData accountData) {
		try {
			Account account = prepareAccount(this.createAccountFactory(accountData), accountData);
			accountDAO.create(account);
			this.accountOperationConstant = AccountOperationConstant.ACCOUNT_CREATED;
			notifyObservers();
		} catch (UnsupportedOperationException ex){
			ex.printStackTrace();
		}
	}

	protected final Account prepareAccount(Account account, AccountData accountData){
		account.setAccountNumber(accountData.getAccountNumber());
		account.setCustomer(accountData.getCustomer());
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.getAccountByAccountNumber(accountNumber);
		if(account != null) {
			account.deposit(amount);
			accountDAO.update(account);
			addToChangedAccountList(account, new AccountTransaction(Action.DEPOSIT, amount));
		} else{
			//TODO: I'm not sure about this?
			Log.getLogger().write("deposited");
		}
		notifyObservers();
	}


	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.getAccountByAccountNumber(accountNumber);
		account.withdraw(amount);
		accountDAO.update(account);
		addToChangedAccountList(account, new AccountTransaction(Action.WITHDRAW, amount));
		notifyObservers();
	}

	public Map<Account, ArrayList<AccountTransaction>> getAccountTransactions() {
		return updatedAccountList;
	}

	public void addToChangedAccountList(Account account, AccountTransaction accTranx) {
		ArrayList<AccountTransaction> transactions;
		if(updatedAccountList.containsKey(account)) {
			transactions = updatedAccountList.get(account);
		} else {
			transactions = new ArrayList<>();
		}
		transactions.add(accTranx);
		updatedAccountList.put(account, transactions);
	}

	public void clearChangedAccountList(){
		updatedAccountList.clear();
	}

	public Account getAccount(String accountNumber) {
		return accountDAO.getAccountByAccountNumber(accountNumber);
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void addInterest() {
		getAllAccountNumbers().stream().map(accountDAO::getAccountByAccountNumber).forEach(account -> {
			account.addInterest();
			accountDAO.update(account);
		});

	}

	public List<String> getAllAccountNumbers(){
		return getAllAccounts().stream()
				.map(Account::getAccountNumber)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.getAccountByAccountNumber(fromAccountNumber);
		Account toAccount = accountDAO.getAccountByAccountNumber(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.update(fromAccount);
		accountDAO.update(toAccount);
	}

	public AccountOperationConstant getAccountOperationConstant() {
		return accountOperationConstant;
	}

	public void generateReport(Account account, GenerateReport generateReport, boolean isBankingSystem) {
		Log.getLogger().write("REPORT GENERATING HAS STARTED !!!");
		Log.getLogger().write(account.toString());
		StringBuilder sb = new StringBuilder();

		if(isBankingSystem) {
			HashMap<LocalDate, List<AccountEntry>> dailyAccountEnties = new HashMap();

			for (AccountEntry accountEntry : account.getAccountEntries()) {
				List<AccountEntry> entries = new ArrayList<>();
				LocalDate reportDate;
				reportDate = accountEntry.getDate();

				if (dailyAccountEnties.containsKey(reportDate)) {
					entries = dailyAccountEnties.get(reportDate);
				}
				entries.add(accountEntry);
				dailyAccountEnties.put(reportDate, entries);
			}

			sb.append(generateReportRows(dailyAccountEnties));
			System.out.println(dailyAccountEnties);
		} else {
			System.out.println(account);
			CreditCardAccount creditCardAccount = (CreditCardAccount)account;
			sb.append("Previous balance: " + creditCardAccount.getPreviousBalance() + "\n");
			sb.append("New balance: " + creditCardAccount.getNewBalance() + "\n");
			sb.append("Total charges: " + creditCardAccount.getTotalCharges() + "\n");
			sb.append("Total credits: " + creditCardAccount.getTotalCredit() + "\n");
			sb.append("Total due: " + creditCardAccount.getTotalDue() + "\n");
		}

		generateReport.setReport(sb.toString());
		Log.getLogger().write(sb.toString());
		Log.getLogger().write("REPORT GENERATION COMPLETED!");
	}

	public String generateReportRows(HashMap<LocalDate, List<AccountEntry>> reportData) {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<LocalDate, List<AccountEntry>> entry : reportData.entrySet()) {
			LocalDate date = entry.getKey();
			List<AccountEntry> accountEntries = entry.getValue();

			sb.append(" Date: " + date + "\n");
			sb.append(" ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n");
			sb.append("|                                                     |\n");

			for(AccountEntry accountEntry : accountEntries) {
				sb.append("         " + accountEntry.report() + "\n");
			}
			sb.append("|＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿|\n");
		}

		return sb.toString();
	}

	@Override
	public void registerObserver(edu.miu.cs525.framework.Observer observer) {
		this.observerList.add(observer);
	}

	@Override
	public void removeObserver(edu.miu.cs525.framework.Observer observer) {
		this.observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		this.observerList.forEach(Observer::update);
	}
}
