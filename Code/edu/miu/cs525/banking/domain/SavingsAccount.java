package edu.miu.cs525.banking.domain;

import edu.miu.cs525.banking.constant.BankingAccountType;
import edu.miu.cs525.banking.visitor.Visitor;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.InterestComputationStrategy;

public class SavingsAccount extends Account {

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
