package backend.banking.visitor;

import backend.commons.CheckingAccounts;
import backend.commons.SavingsAccount;

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
    public double visit(CheckingAccounts checkingAccounts) {
        if (checkingAccounts.getBalance() < 50000) return checkingAccounts.getBalance() * .015;
        else return checkingAccounts.getBalance() * .025;
    }
}
