package backend.banking;

import backend.banking.visitor.Visitable;
import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.AccountType;
import backend.commons.InterestComputationStrategy;

public class SavingsAccount extends Account implements Visitable {
    public SavingsAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
    }
    @Override
    public String getAccountType() {
        return AccountType.SAVINGS.name();
    }
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


}
