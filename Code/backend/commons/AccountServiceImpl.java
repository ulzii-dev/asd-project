package backend.commons;

import backend.banking.visitor.InterestComputerVisitor;
import factory.AccountFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountServiceImpl implements AccountService {
	private AccountFactory accountFactory;
	private final AccountDAO accountDAO;
	private int noOfAccounts;
	private final InterestComputerVisitor interestComputerVisitor =  new InterestComputerVisitor();

	public AccountServiceImpl(AccountFactory accountFactory){
		this.accountFactory = accountFactory;
		accountDAO = new AccountDAOImpl();
	}

	public Account createAccount(String accountNumber, String customerName) {
		Account account = accountFactory.createCheckingAccount();
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);

		accountDAO.createAccount(account);
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		
		accountDAO.updateAccount(account);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	@Override
	public void addInterest() {

		for (String accountNumber : getAllAccountNumbers()) {
			Account account = accountDAO.loadAccount(accountNumber);
			// adding visitor pattern for adding interest
			//account.accept(interestComputerVisitor);
			account.addInterest();
			accountDAO.updateAccount(account);
		}

	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}


    public List<String> getAllAccountNumbers(){
		ArrayList<String> listOfAccntNumbers = new ArrayList<String>();

		for (Account value : getAllAccounts()) {
			listOfAccntNumbers.add(value.getAccountNumber());
		}

		return listOfAccntNumbers;
	}



	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}
}
