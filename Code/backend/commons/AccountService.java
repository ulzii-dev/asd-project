package backend.commons;

import framework.Observable;
import framework.Observer;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AccountService implements Observable {
    private AccountDAO accountDAO;
    private List<Observer> observerList;
    private Map<Account, ArrayList<AccountTransaction>> changedAccountList = new HashMap<>();
    private String report;

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
        this.observerList = new ArrayList<>();
    }

    public final void createAccount(String accountNumber, Customer customer, String accountType) {
        Account account = this.initAccount(accountType, customer);
        account.setCustomer(customer);
        account.setAccountNumber(accountNumber);
        accountDAO.createAccount(account);

        notifyObservers();
    }

    public abstract Account initAccount(String accountType, Customer customer);

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if(account != null) {
            account.deposit(amount);
            accountDAO.updateAccount(account);

            addToChangedAccountList(account, new AccountTransaction(Action.DEPOSIT, amount));
        } else{
            System.out.println("deposited");
        }
        notifyObservers();
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);

        addToChangedAccountList(account, new AccountTransaction(Action.WITHDRAW, amount));
        notifyObservers();
    }

    public void addInterest() {
        Collection<String> actNums = getAllAccounts().stream().map(Account::getAccountNumber).collect(Collectors.toList());
        for (String actNum : actNums) {
            Account account = accountDAO.loadAccount(actNum);
            accountDAO.updateAccount(account);
        }
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        Log.getLogger().write("Registering Observer " + observer.getClass().getSimpleName());
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        Log.getLogger().write("Removing Observer " + observer.getClass().getSimpleName());
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Log.getLogger().write("Notifying to observers!");
        this.observerList.forEach(Observer::update);
    }

    public Map<Account, ArrayList<AccountTransaction>> getAccountTransactions() {
        return changedAccountList;
    }

    public void addToChangedAccountList(Account account, AccountTransaction accTranx) {
        ArrayList<AccountTransaction> transactions;
        if(changedAccountList.containsKey(account)) {
            transactions = changedAccountList.get(account);
        } else {
            transactions = new ArrayList<>();
        }
        transactions.add(accTranx);
        changedAccountList.put(account, transactions);
    }

    public void clearChangedAccountList(){
        changedAccountList.clear();
    }

    public Account getAccount(String accountNumber) {
        return accountDAO.loadAccount(accountNumber);
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
