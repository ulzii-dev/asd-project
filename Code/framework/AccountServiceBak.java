package framework;

import backend.commons.Account;
import backend.commons.AccountDAO;
import backend.commons.Customer;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountServiceBak implements Observable {
    private final AccountDAO accountDAO;
    private final List<Observer> observerList;
    private Account changedAccount;
    private double changedAmount;
    private String report;

    public AccountServiceBak(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
        this.observerList = new ArrayList<>();
    }

    public final void createAccount(String accountNumber, Customer customer, String accountType) {
        Account account = this.initAccount(accountType, customer);
        notifyObservers();
    }

    public abstract Account initAccount(String accountType, Customer customer);

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if(account != null) {
            account.deposit(amount);
            accountDAO.updateAccount(account);
        }else{
            System.out.println("deposited");
        }
        this.changedAccount = account;
        this.changedAmount = amount;
        notifyObservers();
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
        this.changedAccount = account;
        this.changedAmount = amount;
        notifyObservers();
    }

    public void addInterest() {
        notifyObservers();
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
