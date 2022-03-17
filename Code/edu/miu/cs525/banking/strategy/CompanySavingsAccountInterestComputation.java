package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.framework.InterestComputationStrategy;

public class CompanySavingsAccountInterestComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 100000) ? accountBalance * .025 : accountBalance * .035;
    }
}
