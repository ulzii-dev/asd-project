package edu.mum.cs.cs525.labs.exercises.project.backend.banking.strategy;

import edu.mum.cs.cs525.labs.exercises.project.backend.banking.InterestComputationStrategy;

public class CompanySavingsAccountComputation implements InterestComputationStrategy {
    @Override
    public double ComputeInterest(double accountBalance) {
        if (accountBalance < 100000) return accountBalance * .025;
        else return accountBalance * .035;
    }
}
