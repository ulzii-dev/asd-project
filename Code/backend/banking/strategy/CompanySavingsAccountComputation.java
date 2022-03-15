package edu.mum.cs.cs525.labs.exercises.project.backend.banking.strategy;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.InterestComputationStrategy;

public class CompanySavingsAccountComputation implements InterestComputationStrategy {
    @Override
    public double ComputeInterest(double accountBalance) {
        return (accountBalance < 100000) ? accountBalance * .025 : accountBalance * .035;
    }
}
