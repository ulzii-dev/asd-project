package backend.banking.domain;

import backend.banking.constant.BankingAccountType;
import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.Customer;
import backend.commons.InterestComputationStrategy;

public class SavingsAccount extends Account  {

    public SavingsAccount(String accountNumber, String accountType, Customer customer, InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
        setCustomer(customer);
        setAccountNumber(accountNumber);
        setAccountType(accountType);
    }
    @Override
    public String getAccountType() {
        return BankingAccountType.SAVINGS.name();
    }
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


}
