package backend.banking.strategy;

import backend.commons.InterestComputationStrategy;

public class PersonSavingsAccountInterestComputation implements InterestComputationStrategy {

    @Override
    public double computeInterest(double accountBalance) {
        return (accountBalance < 1000) ? accountBalance * .025 : accountBalance * .050;
    }
}
