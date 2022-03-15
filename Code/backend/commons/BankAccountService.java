package edu.mum.cs.cs525.labs.exercises.project.backend.commons;

import edu.mum.cs.cs525.labs.exercises.project.backend.creditcard.observer.EmailSender;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountService;

public class BankAccountService extends AccountService {
    private static volatile BankAccountService instance;

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

    @Override
    public Account initAccount(String accountType, Customer customer) {
        return null;
    }

    public void withdraw(String accountNumber, double amount) {
        Log.getLogger().write("withdraw");
    }

    public void deposit(String accountNumber, double amount) {
        Log.getLogger().write("deposit");
    }
}
