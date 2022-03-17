package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.commons.InterestComputationStrategy;

public class PersonSavingsAccountInterestComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 1000) ? accountBalance * .025 : accountBalance * .050;
    }
}
