package backend.commons;

import framework.AccountOperationConstant;
import framework.Observable;
import framework.Observer;
import framework.ui.UIFrame;

import java.util.*;

public abstract class AccountService implements Observable {
    private final AccountDAO accountDAO;
    private final List<Observer> observerList;
    private final Map<Account, ArrayList<AccountTransaction>> changedAccountList = new HashMap<>();
    protected AccountOperationConstant accountOperationConstant;
    private int noOfAccounts;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
        this.observerList = new ArrayList<Observer>();
        this.registerObserver(UIFrame.getInstance());
        UIFrame.getInstance().setSubject(this);
    }

    public final void createAccount(String accountNumber, Customer customer, String accountType) {
        try {
            Account account = this.createAccountFactory(accountNumber, accountType, customer);
            accountDAO.create(account);
            this.accountOperationConstant = AccountOperationConstant.ACCOUNT_CREATED;
            notifyObservers();
        } catch (UnsupportedOperationException ex) {
            ex.printStackTrace();
        }
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            accountDAO.updateAccount(account);

            addToChangedAccountList(account, new AccountTransaction(Action.DEPOSIT, amount));
        } else {
            Log.getLogger().write("deposited");
        }

        notifyObservers();
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            accountDAO.updateAccount(account);

            addToChangedAccountList(account, new AccountTransaction(Action.WITHDRAW, amount));
        } else {
            Log.getLogger().write("deposited");
        }

        notifyObservers();
    }

    public Map<Account, ArrayList<AccountTransaction>> getAccountTransactions() {
        return changedAccountList;
    }

    public void addToChangedAccountList(Account account, AccountTransaction accTranx) {
        ArrayList<AccountTransaction> transactions;
        if (changedAccountList.containsKey(account)) {
            transactions = changedAccountList.get(account);
        } else {
            transactions = new ArrayList<>();
        }
        transactions.add(accTranx);
        changedAccountList.put(account, transactions);
    }

    public void clearChangedAccountList() {
        changedAccountList.clear();
    }

    public Account getAccount(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public void addInterest() {

        for (String accountNumber : getAllAccountNumbers()) {
            Account account = accountDAO.loadAccount(accountNumber);
            account.addInterest();
            accountDAO.updateAccount(account);
        }

    }

    public List<String> getAllAccountNumbers() {
        ArrayList<String> listOfAccountNumbers = new ArrayList<String>();
        for (Account value : getAllAccounts()) {
            listOfAccountNumbers.add(value.getAccountNumber());
        }
        return listOfAccountNumbers;
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
        Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
        Account toAccount = accountDAO.loadAccount(toAccountNumber);
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.updateAccount(fromAccount);
        accountDAO.updateAccount(toAccount);
    }


    public abstract Account createAccountFactory(String accountNumber, String accountType, Customer customer);

    public AccountOperationConstant getAccountOperationConstant() {
        return accountOperationConstant;
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
