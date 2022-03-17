package edu.miu.cs525.creditcard.strategy;

public class SilverCreditCardCalculator implements CreditCardCalculator {


    @Override
    public double computeInterest(double totalCredit) {
        return totalCredit * 0.08;
    }

    @Override
    public double computeMonthlyMinimumPayment(double totalCredit) {
        return totalCredit * 0.12;
    }

    @Override
    public double computeBalance(double previousBalance, double totalCredit, double totalCharges) {
        return previousBalance - totalCredit + totalCharges + 0.08 * (previousBalance - totalCredit);
    }

    @Override
    public double computeTotalDue(double newBalance) {
        return newBalance * 0.12;
    }
}
