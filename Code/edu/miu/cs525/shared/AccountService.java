package edu.miu.cs525.shared;

import edu.miu.cs525.shared.domain.AccountEntry;
import edu.miu.cs525.shared.dto.AccountDTO;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.Observable;
import edu.miu.cs525.framework.constant.AccountOperationConstant;
import edu.miu.cs525.framework.ui.pages.GenerateReport;
import edu.miu.cs525.framework.ui.pages.UIFrame;
import edu.miu.cs525.shared.dao.AccountDAO;
import edu.miu.cs525.shared.domain.AccountTransaction;
import edu.miu.cs525.shared.log.Log;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AccountService implements Observable {
	private int personalAccountTransferAlertBalance = 400;
	private int creditCardWithdrawAlertBalance = 400;
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

	public abstract Account createAccountFactory(AccountDTO accountDTO);

	public final void createAccount(AccountDTO accountDTO) {
		try {
			Account account = prepareAccount(createAccountFactory(accountDTO), accountDTO);
			accountDAO.create(account);
			notifyObservers();
		} catch (UnsupportedOperationException ex){
			ex.printStackTrace();
		}
	}

	protected final Account prepareAccount(Account account, AccountDTO accountDTO){
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setCustomer(accountDTO.getCustomer());
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		try {
			Account account = accountDAO.getAccountByAccountNumber(accountNumber);
			account.deposit(amount);
			accountDAO.update(account);
			addToChangedAccountList(account, new AccountTransaction(AccountOperationConstant.DEPOSITED, amount));
			notifyObservers();
		}catch (NullPointerException ex){
			ex.printStackTrace();
		}
	}

	public void withdraw(String accountNumber, double amount) {
		try {
			Account account = accountDAO.getAccountByAccountNumber(accountNumber);
			account.withdraw(amount);
			accountDAO.update(account);
			addToChangedAccountList(account, new AccountTransaction(AccountOperationConstant.WITHDRAW, amount));
			notifyObservers();
		}catch (NullPointerException ex){
			ex.printStackTrace();
		}
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
		for (String s : getAllAccountNumbers()) {
			Account account = accountDAO.getAccountByAccountNumber(s);
			double amount = account.addInterest();
			accountDAO.update(account);
			addToChangedAccountList(account, new AccountTransaction(AccountOperationConstant.INTEREST, amount));
		}
		notifyObservers();
	}

	public List<String> getAllAccountNumbers(){
		return getAllAccounts().stream()
				.map(Account::getAccountNumber)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void setPersonalAccountTransferAlertBalance(int checkingBalance) {
		this.personalAccountTransferAlertBalance = checkingBalance;
	}

	public int getPersonalAccountTransferAlertBalance() {
		return personalAccountTransferAlertBalance;
	}

	public void setCreditCardWithdrawAlertBalance(int checkingBalance) {
		this.creditCardWithdrawAlertBalance = checkingBalance;
	}

	public int getCreditCardWithdrawAlertBalance() {
		return creditCardWithdrawAlertBalance;
	}

	public AccountOperationConstant getAccountOperationConstant() {
		return accountOperationConstant;
	}



	public void generateReport(Account account, GenerateReport generateReport, boolean isBankingSystem) {
		Log.getLogger().write("REPORT GENERATING HAS STARTED !!!");
		Log.getLogger().write(account.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("Account: " + account.getCustomer().getName());

		if(isBankingSystem) {
			HashMap<LocalDate, List<AccountEntry>> dailyAccountEntities = new HashMap();

			for (AccountEntry accountEntry : account.getAccountEntries()) {
				List<AccountEntry> entries = new ArrayList<>();
				LocalDate reportDate;
				reportDate = accountEntry.getDate();

				if (dailyAccountEntities.containsKey(reportDate)) {
					entries = dailyAccountEntities.get(reportDate);
				}
				entries.add(accountEntry);
				dailyAccountEntities.put(reportDate, entries);
			}

			sb.append(generateReportRows(dailyAccountEntities));
		} else {
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
	public void registerObserver(Observer observer) {
		this.observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observerList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		this.observerList.forEach(Observer::update);
	}
}
