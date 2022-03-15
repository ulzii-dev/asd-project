package backend.commons;

import backend.creditcard.observer.EmailSender;
import framework.Observable;
import framework.Observer;

import java.util.Collection;

public class BankAccountService implements AccountService, Observable {
    private static volatile BankAccountService instance;

    private BankAccountService() {
        this.registerObserver(new EmailSender(this));
    }

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
        Log.getLogger().write("withdraw");
    }

    public void deposit(String accountNumber, double amount) {
        Log.getLogger().write("deposit");
    }

    @Override
    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
        Log.getLogger().write("transferFunds");
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

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
    //---------------------------------------------------------------------------
}
