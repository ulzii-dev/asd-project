package backend.banking.service;

import backend.banking.builder.AccountData;
import backend.banking.constant.BankingAccountType;
import backend.banking.domain.CheckingAccount;
import backend.banking.domain.SavingsAccount;
import backend.banking.dao.BankingAccountDAO;
import backend.banking.dto.AccountDTO;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.*;
import framework.domain.CompanyAccount;
import framework.domain.PersonalAccount;
import framework.observer.EmailSender;

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
    public Account createAccountFactory(AccountData accountData) {
        Customer customer = accountData.getCustomer();
        BankingAccountType bankAccountType = BankingAccountType.valueOf(accountData.getAccountType());
        if (customer instanceof PersonalAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(new PersonSavingsAccountInterestComputation());
        } else if(customer instanceof CompanyAccount) {
            if (bankAccountType== BankingAccountType.CHECKING) {
                return new CheckingAccount(new CompanyCheckingAccountComputation());
            }
            return new SavingsAccount(new CompanySavingsAccountComputation());
        }
        throw new UnsupportedOperationException("Invalid Account Type! Please Insert valid Account Type");
    }

}
