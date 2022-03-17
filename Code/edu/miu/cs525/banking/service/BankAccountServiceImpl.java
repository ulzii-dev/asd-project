package edu.miu.cs525.banking.service;

import edu.miu.cs525.shared.builder.AccountDTO;
import edu.miu.cs525.banking.constant.BankingAccountType;
import edu.miu.cs525.banking.domain.CheckingAccount;
import edu.miu.cs525.banking.domain.SavingsAccount;
import edu.miu.cs525.banking.dao.BankingAccountDAOImpl;
import edu.miu.cs525.banking.strategy.CompanyCheckingAccountInterestComputation;
import edu.miu.cs525.banking.strategy.CompanySavingsAccountInterestComputation;
import edu.miu.cs525.banking.strategy.PersonalCheckingAccountInterestComputation;
import edu.miu.cs525.banking.strategy.PersonalSavingsAccountInterestComputation;
import edu.miu.cs525.shared.Account;
import edu.miu.cs525.shared.AccountService;
import edu.miu.cs525.shared.domain.Customer;
import edu.miu.cs525.framework.domain.CompanyAccount;
import edu.miu.cs525.framework.domain.PersonalAccount;
import edu.miu.cs525.framework.observer.EmailSender;

public class BankAccountServiceImpl extends AccountService {
    private static volatile BankAccountServiceImpl instance;

    private BankAccountServiceImpl() {
        super(BankingAccountDAOImpl.getInstance());
        this.registerObserver(new EmailSender(this));
    }

    public static BankAccountServiceImpl getInstance() {
        if (instance == null) {
            synchronized (BankAccountServiceImpl.class) {
                if (instance == null) {
                    instance = new BankAccountServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccountFactory(AccountDTO accountDTO) throws UnsupportedOperationException {
        Customer customer = accountDTO.getCustomer();
        BankingAccountType bankAccountType = BankingAccountType.valueOf(accountDTO.getAccountType());
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
