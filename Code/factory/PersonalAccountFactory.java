package factory;

import backend.banking.CheckingAccount;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.Account;
import backend.commons.Customer;

public class PersonalAccountFactory extends AccountFactory {

    @Override
    public Account createCheckingAccount() {
        return new CheckingAccount(new PersonCheckingAccountComputation());
    }
    @Override
    public Account createSavingsAccount(){
        return new CheckingAccount(new PersonSavingsAccountInterestComputation());
    }
    @Override
    public Account createCreditCardAccount() {
        return null;
    }


}
