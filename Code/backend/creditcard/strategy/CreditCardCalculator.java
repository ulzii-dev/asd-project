package backend.creditcard.strategy;

public interface CreditCardCalculator {

    public double computeMonthlyInterest(double totalCredit);
    public double computeMonthlyMinimumPayment(double totalCredit);
    public double computeBalance(double previousBalance, double totalCredit,double totalCharges);
    public double computeTotalDue(double newBalance);


}
