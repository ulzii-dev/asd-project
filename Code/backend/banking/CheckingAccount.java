package backend.banking;

import backend.commons.Account;
import backend.commons.AccountType;
import backend.commons.InterestComputationStrategy;

public class CheckingAccount extends Account {
    public CheckingAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
    }

    @Override
    public String getAccountType() {
        return AccountType.Checking.name();
    }
}
