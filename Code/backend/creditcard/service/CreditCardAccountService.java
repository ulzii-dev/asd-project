package backend.creditcard.service;

import backend.commons.Account;
import backend.commons.AccountService;
import backend.commons.Customer;
import backend.creditcard.CreditCardAccount;
import backend.creditcard.CreditCardType;
import backend.creditcard.dao.CreditCardAccountDAO;
import backend.creditcard.strategy.BronzeCreditCardCaluclator;
import backend.creditcard.strategy.GoldCreditCardcalculator;
import backend.creditcard.strategy.SilverCreditCardCalculator;
import framework.observer.EmailSender;

public class CreditCardAccountService extends AccountService {
    private static volatile CreditCardAccountService instance;

    private CreditCardAccountService() {
        super(new CreditCardAccountDAO());
        this.registerObserver(new EmailSender(this));
    }

    public static CreditCardAccountService getInstance() {
        if (instance == null) {
            synchronized (CreditCardAccountService.class) {
                if (instance == null) {
                    instance = new CreditCardAccountService();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccountFactory(String accountNumber, String accountType, Customer customer) {
        CreditCardType type = CreditCardType.valueOf(accountType);
        if (type.equals(CreditCardType.BRONZE)) {
            return new CreditCardAccount(accountNumber, accountType, customer, new BronzeCreditCardCaluclator(), type);
        }
        if (type.equals(CreditCardType.SILVER)) {
            return new CreditCardAccount(accountNumber, accountType, customer, new SilverCreditCardCalculator(), type);
        }
        if (type.equals(CreditCardType.GOLD)) {
            return new CreditCardAccount(accountNumber, accountType, customer, new GoldCreditCardcalculator(), type);
        }
        throw new UnsupportedOperationException("Invalid Credit Card Type!");
    }

}
