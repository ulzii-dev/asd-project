package backend.banking.service;

import backend.banking.CheckingAccount;
import backend.banking.dao.BankingAccountDAO;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.*;
import backend.creditcard.observer.EmailSender;
import framework.Observable;
import framework.Observer;
import ui.PersonalAccount;

import java.util.Collection;

public class BankAccountService extends AccountService {
    private static volatile BankAccountService instance;

    private BankAccountService() {
        super(BankingAccountDAO.getInstance());
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
    public Account accountFactory(String accountType, Customer customer) {
        if (customer instanceof PersonalAccount) {
            if (AccountType.valueOf(accountType) == AccountType.CHECKING) {
                return new CheckingAccount(new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(new PersonSavingsAccountInterestComputation());
        }
        if (AccountType.valueOf(accountType) == AccountType.CHECKING) {
            return new CheckingAccount(new CompanyCheckingAccountComputation());
        }
        return new SavingsAccount(new CompanySavingsAccountComputation());
    }

}
