package edu.mum.cs.cs525.labs.exercises.project.backend.banking.strategy;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.InterestComputationStrategy;

public class CompanyCheckingAccountComputation implements InterestComputationStrategy {
    @Override
    public double ComputeInterest(double accountBalance) {
        return (accountBalance < 50000) ? accountBalance * .015 : accountBalance * .025;
    }
}
