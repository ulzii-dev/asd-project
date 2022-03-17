package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.shared.InterestComputationStrategy;

public class CompanyCheckingAccountInterestComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 50000) ? accountBalance * .015 : accountBalance * .025;
    }
}
