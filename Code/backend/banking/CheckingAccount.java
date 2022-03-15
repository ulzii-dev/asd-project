package backend.banking;

import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.AccountType;
import backend.commons.InterestComputationStrategy;

public class CheckingAccount extends Account {
    public CheckingAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
    }

    @Override
    public double accept(Visitor visitor) {
        return 0;
    }

    @Override
    public String getAccountType() {
        return AccountType.Checking.name();
    }
}
