package edu.miu.cs525.creditcard.service;

import edu.miu.cs525.creditcard.strategy.BronzeCreditCardCalculator;
import edu.miu.cs525.framework.observer.EmailSender;
import edu.miu.cs525.shared.dto.AccountDTO;
import edu.miu.cs525.shared.Account;
import edu.miu.cs525.shared.AccountService;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.creditcard.dao.CreditCardAccountDAOImpl;
import edu.miu.cs525.creditcard.strategy.GoldCreditCardCalculator;
import edu.miu.cs525.creditcard.strategy.SilverCreditCardCalculator;

public class CreditCardAccountServiceImpl extends AccountService {
    private static volatile CreditCardAccountServiceImpl instance;

    private CreditCardAccountServiceImpl() {
        super(new CreditCardAccountDAOImpl());
        setPersonalAccountTransferAlertBalance(400);
        this.registerObserver(new EmailSender(this));
    }

    public static CreditCardAccountServiceImpl getInstance() {
        if (instance == null) {
            synchronized (CreditCardAccountServiceImpl.class) {
                if (instance == null) {
                    instance = new CreditCardAccountServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Account createAccountFactory(AccountDTO accountDTO){
        CreditCardType creditCardType = CreditCardType.valueOf(accountDTO.getAccountType());
        if(creditCardType.equals(CreditCardType.BRONZE)){
            return new CreditCardAccount(new BronzeCreditCardCalculator(), creditCardType);
        }else if(creditCardType.equals(CreditCardType.SILVER)){
            return new CreditCardAccount(new SilverCreditCardCalculator(), creditCardType);
        }else if(creditCardType.equals(CreditCardType.GOLD)){
            return new CreditCardAccount(new GoldCreditCardCalculator(), creditCardType);
        }else {
            throw new UnsupportedOperationException("Invalid Credit Card Type!");
        }
    }

}
