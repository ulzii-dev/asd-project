package edu.miu.cs525.creditcard.service;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountService;
import edu.miu.cs525.commons.observer.EmailSender;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.creditcard.dao.CreditCardAccountDAO;
import edu.miu.cs525.creditcard.strategy.BronzeCreditCardCalculator;
import edu.miu.cs525.creditcard.strategy.GoldCreditCardCalculator;
import edu.miu.cs525.creditcard.strategy.SilverCreditCardCalculator;

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
    public Account createAccountFactory(AccountData accountData){
        System.out.println(" ---> " + accountData.getAccountType());
        CreditCardType creditCardType = CreditCardType.valueOf(accountData.getAccountType());

        switch (creditCardType) {
            case BRONZE: return new CreditCardAccount(new BronzeCreditCardCalculator(), creditCardType);
            case SILVER: return new CreditCardAccount(new SilverCreditCardCalculator(), creditCardType);
            case GOLD: return new CreditCardAccount(new GoldCreditCardCalculator(), creditCardType);
            default: throw new UnsupportedOperationException("Invalid Credit Card Type!");
        }
    }
}
