package edu.miu.cs525.commons;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.framework.Observer;
import edu.miu.cs525.framework.Observable;
import edu.miu.cs525.framework.AccountOperationConstant;
import edu.miu.cs525.framework.ui.pages.UIFrame;

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
			Account account = prepareAccount(createAccountFactory(accountData), accountData);
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
