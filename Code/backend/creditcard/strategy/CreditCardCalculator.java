package backend.creditcard.strategy;

import backend.commons.InterestComputationStrategy;

public interface CreditCardCalculator extends InterestComputationStrategy {

    public double computeInterest(double totalCredit);
    public double computeMonthlyMinimumPayment(double totalCredit);
    public double computeBalance(double previousBalance, double totalCredit,double totalCharges);
    public double computeTotalDue(double newBalance);



}
