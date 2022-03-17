package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.framework.InterestComputationStrategy;

public class PersonalSavingsAccountInterestComputation implements InterestComputationStrategy {

    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 1000) ? accountBalance * .025 : accountBalance * .050;
    }
}
