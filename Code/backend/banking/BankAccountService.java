package edu.mum.cs.cs525.labs.exercises.project.backend.banking;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.Account;
import edu.mum.cs.cs525.labs.exercises.project.backend.commons.Customer;
import edu.mum.cs.cs525.labs.exercises.project.backend.creditcard.observer.EmailSender;
import edu.mum.cs.cs525.labs.exercises.project.framework.AccountService;
import edu.mum.cs.cs525.labs.exercises.project.framework.Observable;

public class BankAccountService extends AccountService {
    private static volatile BankAccountService instance;

    @Override
    public Account initAccount(String accountType, Customer customer) {
        return null;
    }

    private BankAccountService() {
        super(BankAccountDAO.getInstance());
        this.registerObserver(new EmailSender(this));
    }

    public static BankAccountService getInstance(){
        if(instance == null) {
            synchronized (BankAccountService.class) {
                if(instance == null) {
                    instance = new BankAccountService();
                }
            }
        }

        return instance;
    }
}
