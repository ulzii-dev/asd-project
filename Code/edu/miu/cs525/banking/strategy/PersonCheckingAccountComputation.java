package edu.miu.cs525.banking.strategy;

import edu.miu.cs525.commons.InterestComputationStrategy;

public class PersonCheckingAccountComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 1000) ? accountBalance * .015 : accountBalance * .025;
    }
}
