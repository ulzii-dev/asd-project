package edu.miu.cs525.creditcard.service;

import edu.miu.cs525.commons.builder.AccountData;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountService;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.creditcard.dao.CreditCardAccountDAO;
import edu.miu.cs525.creditcard.strategy.BronzeCreditCardCalcuclator;
import edu.miu.cs525.creditcard.strategy.GoldCreditCardCalculator;
import edu.miu.cs525.creditcard.strategy.SilverCreditCardCalculator;
import edu.miu.cs525.framework.observer.EmailSender;

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
        if(creditCardType.equals(CreditCardType.BRONZE)){
            return new CreditCardAccount(new BronzeCreditCardCalcuclator(), creditCardType);
        }else if(creditCardType.equals(CreditCardType.SILVER)){
            return new CreditCardAccount(new SilverCreditCardCalculator(), creditCardType);
        }else if(creditCardType.equals(CreditCardType.GOLD)){
            return new CreditCardAccount(new GoldCreditCardCalculator(), creditCardType);
        }else {
            throw new UnsupportedOperationException("Invalid Credit Card Type!");
        }
    }

}
