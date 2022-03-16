package backend.banking.domain;

import backend.banking.constant.BankingAccountType;
import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.Customer;
import backend.commons.InterestComputationStrategy;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountNumber, String accountType, Customer customer, InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
        setAccountType(accountType);
        setCustomer(customer);
        setAccountNumber(accountNumber);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getAccountType() {
        return BankingAccountType.CHECKING.name();
    }
}
