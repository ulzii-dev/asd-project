package backend.banking.strategy;

import backend.commons.InterestComputationStrategy;

public class CompanySavingsAccountComputation implements InterestComputationStrategy {
    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 100000) ? accountBalance * .025 : accountBalance * .035;
    }
}
