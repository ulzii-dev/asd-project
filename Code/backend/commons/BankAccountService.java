package backend.commons;

import backend.creditcard.observer.EmailSender;
import framework.Observable;
import framework.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BankAccountService extends AccountService implements Observable {
    private static volatile BankAccountService instance;
    private AccountDAO accountDAO = new AccountDAOImpl();
    private List<Observer> observerList =  new ArrayList<>();

    private List<Account> changedAccountList = new ArrayList<>();

    private BankAccountService() {
        super(BankAccountDAO.getInstance());
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
    public Account getAccount(String accountNumber) {
        return null;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return null;
    }
    //---------------------------------------------------------------------------

    // Transactions -------------------------------------------------------------
//    public void withdraw(String accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        if(account != null) {
//            account.withdraw(amount);
//            accountDAO.updateAccount(account);
//
//            addToChangedAccountList(account);
//
//            Log.getLogger().write("withdraw");
//            notifyObservers();
//        } else {
//            Log.getLogger().error("BankAccountService: Withdraw => Account is NULL!");
//        }
//    }

    @Override
    public Account initAccount(String accountType, Customer customer) {
        return null;
    }

//    public void deposit(String accountNumber, double amount) {
//        Account account = accountDAO.loadAccount(accountNumber);
//        if(account != null) {
//            account.deposit(amount);
//            accountDAO.updateAccount(account);
//
//            addToChangedAccountList(account);
//
//            Log.getLogger().write("deposit");
//            notifyObservers();
//        } else {
//            Log.getLogger().error("BankAccountService: Deposit => Account is NULL!");
//        }
//    }

//    @Override
//    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
//        Log.getLogger().write("transferFunds");
//        notifyObservers();
//    }

    //---------------------------------------------------------------------------

    // Interest -----------------------------------------------------------------
    @Override
    public void addInterest() {

    }
    //---------------------------------------------------------------------------
}
