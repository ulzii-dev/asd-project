package edu.miu.cs525.banking.service;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.banking.constant.BankingAccountType;
import edu.miu.cs525.banking.domain.CheckingAccount;
import edu.miu.cs525.banking.domain.SavingsAccount;
import edu.miu.cs525.banking.dao.BankingAccountDAO;
import edu.miu.cs525.banking.strategy.CompanyCheckingAccountComputation;
import edu.miu.cs525.banking.strategy.CompanySavingsAccountComputation;
import edu.miu.cs525.banking.strategy.PersonCheckingAccountComputation;
import edu.miu.cs525.banking.strategy.PersonSavingsAccountInterestComputation;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountService;
import edu.miu.cs525.commons.Customer;
import edu.miu.cs525.framework.domain.CompanyAccount;
import edu.miu.cs525.framework.domain.PersonalAccount;
import edu.miu.cs525.framework.observer.EmailSender;

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
    public Account createAccountFactory(AccountData accountData) throws UnsupportedOperationException {
        Customer customer = accountData.getCustomer();
        BankingAccountType bankAccountType = BankingAccountType.valueOf(accountData.getAccountType());
        return getConcreteAccountObject(customer, bankAccountType);
    }

    private Account getConcreteAccountObject(Customer customer, BankingAccountType bankAccountType) {
        if (customer instanceof PersonalAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(new PersonSavingsAccountInterestComputation());
        } else if(customer instanceof CompanyAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(new CompanyCheckingAccountComputation());
            }
            return new SavingsAccount(new CompanySavingsAccountComputation());
        }
        throw new UnsupportedOperationException("Invalid Account Type! Please Insert valid Account Type");
    }

}
