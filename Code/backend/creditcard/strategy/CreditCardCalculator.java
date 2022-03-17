package backend.creditcard.strategy;

import backend.commons.InterestComputationStrategy;

public interface CreditCardCalculator extends InterestComputationStrategy {

    double computeInterest(double totalCredit);

    double computeMonthlyMinimumPayment(double totalCredit);

    double computeBalance(double previousBalance, double totalCredit, double totalCharges);

    double computeTotalDue(double newBalance);


}
