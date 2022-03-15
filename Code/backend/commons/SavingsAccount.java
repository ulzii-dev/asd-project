package backend.commons;

import backend.banking.visitor.Visitable;
import backend.banking.visitor.Visitor;

public class SavingsAccount extends Account implements Visitable {
    public SavingsAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
    }

    @Override
    public String getAccountType() {
        return AccountType.Saving.name();
    }

    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String getAccountTypes(){
        return AccountTypes.Saving.name();
    }
}
