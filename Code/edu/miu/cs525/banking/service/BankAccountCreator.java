package edu.miu.cs525.banking.service;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.banking.constant.BankingAccountType;
import edu.miu.cs525.banking.domain.CheckingAccount;
import edu.miu.cs525.banking.domain.SavingsAccount;
import edu.miu.cs525.banking.dao.BankingAccountDAO;
import edu.miu.cs525.banking.strategy.CompanyCheckingAccountInterestComputation;
import edu.miu.cs525.banking.strategy.CompanySavingsAccountInterestComputation;
import edu.miu.cs525.banking.strategy.PersonalCheckingAccountInterestComputation;
import edu.miu.cs525.banking.strategy.PersonalSavingsAccountInterestComputation;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountService;
import edu.miu.cs525.commons.Customer;
import edu.miu.cs525.framework.domain.CompanyAccount;
import edu.miu.cs525.framework.domain.PersonalAccount;
import edu.miu.cs525.framework.observer.EmailSender;

public class BankAccountCreator extends AccountService {
    private static volatile BankAccountCreator instance;

    private BankAccountCreator() {
        super(BankingAccountDAO.getInstance());
        this.registerObserver(new EmailSender(this));
    }

    public static BankAccountCreator getInstance() {
        if (instance == null) {
            synchronized (BankAccountCreator.class) {
                if (instance == null) {
                    instance = new BankAccountCreator();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccountFactory(AccountData accountData) throws UnsupportedOperationException {
        Customer customer = accountData.getCustomer();
        BankingAccountType bankAccountType = BankingAccountType.valueOf(accountData.getAccountType());
        return getConcreteAccountObject(customer, bankAccountType);
    }

    private Account getConcreteAccountObject(Customer customer, BankingAccountType bankAccountType) {
        if (customer instanceof PersonalAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(new PersonalCheckingAccountInterestComputation());
            }
            return new SavingsAccount(new PersonalSavingsAccountInterestComputation());
        } else if(customer instanceof CompanyAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(new CompanyCheckingAccountInterestComputation());
            }
            return new SavingsAccount(new CompanySavingsAccountInterestComputation());
        }
        throw new UnsupportedOperationException("Invalid Account Type! Please Insert valid Account Type");
    }

}
