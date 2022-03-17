package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.framework.InterestComputationStrategy;

public class PersonalCheckingAccountInterestComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 1000) ? accountBalance * .015 : accountBalance * .025;
    }
}
