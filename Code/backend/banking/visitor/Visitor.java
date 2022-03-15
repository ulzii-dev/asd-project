package backend.banking.visitor;

import backend.commons.CheckingAccounts;
import backend.commons.SavingsAccount;

public interface Visitor {

    public double visit(SavingsAccount savingsAccount);

    public double visit(CheckingAccounts checkingAccounts);

}
