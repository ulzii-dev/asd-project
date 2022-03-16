package backend.banking;

import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.InterestComputationStrategy;

public class SavingsAccount extends Account  {
    public SavingsAccount(InterestComputationStrategy interestComputationStrategy) {
        super(interestComputationStrategy);
    }
    @Override
    public String getAccountType() {
        return BankingAccountType.SAVINGS.name();
    }
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


}
