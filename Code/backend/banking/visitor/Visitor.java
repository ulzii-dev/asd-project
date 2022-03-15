package backend.banking.visitor;

import backend.commons.CheckingAccounts;
import backend.commons.SavingsAccount;

public interface Visitor {

    double visit(SavingsAccount savingsAccount);

    double visit(CheckingAccounts checkingAccounts);

}
