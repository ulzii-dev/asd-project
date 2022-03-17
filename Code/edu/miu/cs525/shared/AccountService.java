package edu.miu.cs525.shared;

import edu.miu.cs525.shared.builder.AccountDTO;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.Observable;
import edu.miu.cs525.framework.constant.AccountOperationConstant;
import edu.miu.cs525.framework.ui.pages.UIFrame;
import edu.miu.cs525.shared.dao.AccountDAO;
import edu.miu.cs525.shared.domain.AccountTransaction;
import edu.miu.cs525.shared.log.Log;

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

	public abstract Account createAccountFactory(AccountDTO accountDTO);

	public final void createAccount(AccountDTO accountDTO) {
		try {
			Account account = prepareAccount(createAccountFactory(accountDTO), accountDTO);
			accountDAO.create(account);
			Log.getLogger().write("Account Created Successfully for Customer: " + account.getCustomer().getName());
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
