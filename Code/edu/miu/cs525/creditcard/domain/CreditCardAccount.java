package edu.miu.cs525.creditcard.domain;

import edu.miu.cs525.banking.visitor.Visitor;
import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountEntry;
import edu.miu.cs525.creditcard.constant.CreditCardType;
import edu.miu.cs525.creditcard.strategy.CreditCardCalculator;

import java.time.LocalDate;

public class CreditCardAccount extends Account {
    LocalDate todayDate = LocalDate.now();
    CreditCardCalculator creditCardCalculator;
    CreditCardType creditCardType;

    public CreditCardAccount(CreditCardCalculator creditCardCalculator, CreditCardType creditCardType) {
        super(creditCardCalculator);
        this.creditCardCalculator =  creditCardCalculator;
        this.creditCardType =  creditCardType;
    }
    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


    public double getPreviousBalance() {
        double prevBalance = 0;
        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isBefore(todayDate.withDayOfMonth(1))) {
                prevBalance += accountEntry.getAmount();

            }
        }

        return prevBalance;
    }

    public double getTotalCredit() {

        double totalCredit = 0;

        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isAfter(todayDate.withDayOfMonth(1))) {
                if (accountEntry.getAmount() >= 0) {
                    totalCredit += accountEntry.getAmount();
                }
            }
        }

        return totalCredit;
    }

    public double getTotalCharges() {

        double totalCredit = 0;

        for (AccountEntry accountEntry : getAccountEntries()) {
            if (accountEntry.getDate().isAfter(todayDate.withDayOfMonth(1))) {
                if (accountEntry.getAmount() < 0) {
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

    public double getMonthlyInterest() {
        return this.creditCardCalculator.
                computeInterest(getTotalCredit());
    }


    @Override
    public String getAccountType() {
        return creditCardType.name();
    }

}
