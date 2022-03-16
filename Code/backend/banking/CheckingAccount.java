package backend.banking;

import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.InterestComputationStrategy;

public class CheckingAccount extends Account {
    public CheckingAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
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
