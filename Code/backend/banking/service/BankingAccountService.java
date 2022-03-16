package backend.banking.service;

import backend.banking.BankingAccountType;
import backend.banking.CheckingAccount;
import backend.banking.SavingsAccount;
import backend.banking.dao.BankingAccountDAO;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.*;
import backend.creditcard.observer.EmailSender;
import ui.CompanyAccount;
import ui.PersonalAccount;

public class BankingAccountService extends AccountService {
    private static volatile BankingAccountService instance;

    private BankingAccountService() {
        super(BankingAccountDAO.getInstance());
        this.registerObserver(new EmailSender(this));
    }

    public static BankingAccountService getInstance() {
        if (instance == null) {
            synchronized (BankingAccountService.class) {
                if (instance == null) {
                    instance = new BankingAccountService();
                }
            }
        }

        return instance;
    }


    @Override
    public Account createAccountFactory(String accountType, Customer customer) {
        if (customer instanceof PersonalAccount) {
            if (BankingAccountType.valueOf(accountType) == BankingAccountType.CHECKING) {
                return new CheckingAccount(new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(new PersonSavingsAccountInterestComputation());
        } else if(customer instanceof CompanyAccount) {
            if (BankingAccountType.valueOf(accountType) == BankingAccountType.CHECKING) {
                return new CheckingAccount(new CompanyCheckingAccountComputation());
            }
            return new SavingsAccount(new CompanySavingsAccountComputation());
        }
        return null;
    }

}
