package backend.commons;

import backend.creditcard.observer.EmailSender;
import framework.Observable;
import framework.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BankAccountService implements AccountService, Observable {
    private static volatile BankAccountService instance;
    private AccountDAO accountDAO = new AccountDAOImpl();
    private List<Observer> observerList =  new ArrayList<>();

    private List<Account> changedAccountList = new ArrayList<>();

    public static BankAccountService getInstance() {
        if (instance == null) {
            synchronized (BankAccountService.class) {
                if (instance == null) {
                    instance = new BankAccountService();
                }
            }
        }

        return instance;
    }

    // Account  -----------------------------------------------------------------
    @Override
    public Account createAccount(String accountNumber, String customerName) {
        return null;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return null;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return null;
    }
    //---------------------------------------------------------------------------

    // Transactions -------------------------------------------------------------
    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if(account != null) {
            account.withdraw(amount);
            accountDAO.updateAccount(account);

            addToChangedAccountList(account);

            Log.getLogger().write("withdraw");
            notifyObservers();
        } else {
            Log.getLogger().error("BankAccountService: Withdraw => Account is NULL!");
        }
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        if(account != null) {
            account.deposit(amount);
            accountDAO.updateAccount(account);

            addToChangedAccountList(account);

            Log.getLogger().write("deposit");
            notifyObservers();
        } else {
            Log.getLogger().error("BankAccountService: Deposit => Account is NULL!");
        }
    }

    @Override
    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
        Log.getLogger().write("transferFunds");
        notifyObservers();
    }

    @Override
    public void addToChangedAccountList(Account account) {

    }
    //---------------------------------------------------------------------------

    // Interest -----------------------------------------------------------------
    @Override
    public void addInterest() {

    }
    //---------------------------------------------------------------------------

    // Observer -----------------------------------------------------------------
    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Log.getLogger().write("Notifying to observers");
        observerList.forEach(Observer::update);
    }

    public Collection<Account> getChangedAccounts() {
        return changedAccountList;
    }

    public void clearChangedAccountList(){
        changedAccountList.clear();
    }
    //---------------------------------------------------------------------------
}
