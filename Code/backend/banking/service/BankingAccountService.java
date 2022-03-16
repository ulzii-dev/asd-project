package backend.banking.service;

import backend.banking.constant.BankingAccountType;
import backend.banking.dao.BankingAccountDAO;
import backend.banking.domain.CheckingAccount;
import backend.banking.domain.SavingsAccount;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.Account;
import backend.commons.AccountService;
import backend.commons.Customer;
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
    public Account createAccountFactory(String accountNumber, String accountType, Customer customer) {
        BankingAccountType bankAccountType = BankingAccountType.valueOf(accountType);
        if (customer instanceof PersonalAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(
                        accountNumber,
                        accountType,
                        customer,
                        new PersonCheckingAccountComputation());
            }
            return new SavingsAccount(
                    accountNumber,
                    accountType,
                    customer,
                    new PersonSavingsAccountInterestComputation());
        } else if (customer instanceof CompanyAccount) {
            if (bankAccountType == BankingAccountType.CHECKING) {
                return new CheckingAccount(
                        accountNumber,
                        accountType,
                        customer,
                        new CompanyCheckingAccountComputation());
            }
            return new SavingsAccount(
                    accountNumber,
                    accountType,
                    customer,
                    new CompanySavingsAccountComputation());
        }
        throw new UnsupportedOperationException("Invalid Account Type! Please Insert valid Account Type");
    }

}
