package backend.banking.visitor;

import backend.banking.CheckingAccount;
import backend.banking.SavingsAccount;
import backend.creditcard.CreditCardAccount;

import java.text.DecimalFormat;

public class InterestComputerVisitor implements Visitor {

    DecimalFormat df = new DecimalFormat("##.##");

    public InterestComputerVisitor() {

    }

    @Override
    public double visit(SavingsAccount savingsAccount) {
        if (savingsAccount.getBalance() < 50000) return savingsAccount.getBalance() * .015;
        else return savingsAccount.getBalance() * .025;
    }

    @Override
    public double visit(CheckingAccount checkingAccounts) {
        if (checkingAccounts.getBalance() < 50000) return checkingAccounts.getBalance() * .015;
        else return checkingAccounts.getBalance() * .025;
    }

    @Override
    public double visit(CreditCardAccount creditCardAccount) {
        String type =  creditCardAccount.getAccountType();
        return  switch (type) {
            case "Gold" -> creditCardAccount.getTotalCredit() * 0.06;
            case "Bronze" -> creditCardAccount.getTotalCredit() * 0.1;
            case "Silver" -> creditCardAccount.getTotalCredit() * 0.08;

        };


    }
}
