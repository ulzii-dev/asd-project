package backend.creditcard.service;

import backend.banking.builder.AccountData;
import backend.commons.Account;
import backend.commons.AccountService;
import backend.creditcard.CreditCardAccount;
import backend.creditcard.CreditCardType;
import backend.creditcard.dao.CreditCardAccountDAO;
import backend.creditcard.strategy.BronzeCreditCardCaluclator;
import backend.creditcard.strategy.GoldCreditCardCalculator;
import backend.creditcard.strategy.SilverCreditCardCalculator;
import backend.banking.observer.EmailSender;

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
    public Account createAccountFactory(AccountData accountData) throws UnsupportedOperationException{
        CreditCardType type = CreditCardType.valueOf(accountData.getAccountType());
        if(type.equals(CreditCardType.BRONZE)){
            return new CreditCardAccount(new BronzeCreditCardCaluclator(), type);
        }
        if(type.equals(CreditCardType.SILVER)){
            return new CreditCardAccount(new SilverCreditCardCalculator(), type);
        }
        if(type.equals(CreditCardType.GOLD)){
            return new CreditCardAccount(new GoldCreditCardCalculator(), type);
        }
        throw new UnsupportedOperationException("Invalid Credit Card Type!");
    }

}
