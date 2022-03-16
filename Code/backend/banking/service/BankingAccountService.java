package backend.banking.service;

import backend.banking.constant.BankingAccountType;
import backend.banking.domain.CheckingAccount;
import backend.banking.domain.SavingsAccount;
import backend.banking.dao.BankingAccountDAO;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.*;
import backend.creditcard.observer.EmailSender;
import framework.domain.CompanyAccount;
import framework.domain.PersonalAccount;

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
    public Account createAccountFactory(String accountNumber, String accountType, Customer customer) {
        if (customer instanceof PersonalAccount) {
            if (BankingAccountType.valueOf(accountType) == BankingAccountType.CHECKING) {
                return new CheckingAccount(accountNumber, accountType, customer, new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(accountNumber, accountType, customer, new PersonSavingsAccountInterestComputation());
        } else if(customer instanceof CompanyAccount) {
            if (BankingAccountType.valueOf(accountType) == BankingAccountType.CHECKING) {
                return new CheckingAccount(accountNumber, accountType, customer, new CompanyCheckingAccountComputation());
            }
            return new SavingsAccount(accountNumber, accountType, customer, new CompanySavingsAccountComputation());
        }
        return null;
    }

}
