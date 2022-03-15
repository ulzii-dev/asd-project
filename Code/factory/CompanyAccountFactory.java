package factory;

import backend.banking.CheckingAccount;
import backend.banking.strategy.CompanyCheckingAccountComputation;
import backend.banking.strategy.CompanySavingsAccountComputation;
import backend.banking.strategy.PersonCheckingAccountComputation;
import backend.banking.strategy.PersonSavingsAccountInterestComputation;
import backend.commons.Account;
import backend.commons.Customer;
import backend.commons.SavingsAccount;

public class CompanyAccountFactory extends AccountFactory {

    @Override
    public Account createCheckingAccount() {
        return new CheckingAccount(new CompanyCheckingAccountComputation());
    }
    @Override
    public Account createSavingsAccount(){
        return new SavingsAccount(new CompanySavingsAccountComputation());
    }
    @Override
    public Account createCreditCardAccount() {
        return null;
    }
}
