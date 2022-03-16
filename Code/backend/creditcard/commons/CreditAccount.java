package backend.creditcard.commons;

import backend.banking.visitor.Visitor;
import backend.commons.Account;
import backend.commons.AccountEntry;
import backend.creditcard.strategy.CreditCardCalculator;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditAccount extends Account {
    LocalDate todaydate = LocalDate.now();
    CreditCardCalculator creditCardCalculator;
    public CreditAccount(CreditCardCalculator creditCardCalculator) {

        this.creditCardCalculator =  creditCardCalculator;
    }

    @Override
    public double accept(Visitor visitor) {
        return 0;
    }

    @Override
    public String getAccountType() {
        return null;
    }

    public double getPreviousBalance() {
        double prevBalance = 0;
        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isBefore(todaydate.withDayOfMonth(1))) {
                prevBalance += accountEntry.getAmount();

            }
        }

        return prevBalance;
    }

    public double getTotalCredit() {

        double totalCredit = 0;

        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isAfter(todaydate.withDayOfMonth(1))) {
                if (accountEntry.getAmount()<0){
                    totalCredit += accountEntry.getAmount();
                }
            }
        }

        return totalCredit;
    }

    public double getTotalCharges() {

        double totalCredit = 0;

        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isAfter(todaydate.withDayOfMonth(1))) {
                if (accountEntry.getAmount()>=0){
                    totalCredit += accountEntry.getAmount();
                }
            }
        }

        return totalCredit;

    }
    public double getNewBalance() {
        return this.creditCardCalculator.computeBalance(getPreviousBalance(),
                getTotalCredit(), getTotalCharges());
    }

    public double getTotalDue() {
        return this.creditCardCalculator.
                computeTotalDue(getNewBalance());
    }

    public double getMonthlyMinimumPayment() {
        return this.creditCardCalculator.computeMonthlyMinimumPayment(getTotalCredit());
    }

    public double getMonthlyInterest(){
        return this.creditCardCalculator.
                computeMonthlyInterest(getTotalCredit());
    }




}
