package edu.miu.cs525.creditcard.strategy;

import edu.miu.cs525.framework.InterestComputationStrategy;

public interface CreditCardCalculator extends InterestComputationStrategy {

    double computeInterest(double totalCredit);

    double computeMonthlyMinimumPayment(double totalCredit);

    double computeBalance(double previousBalance, double totalCredit, double totalCharges);

    double computeTotalDue(double newBalance);
}