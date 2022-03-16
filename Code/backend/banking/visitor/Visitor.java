package backend.banking.visitor;

import backend.banking.CheckingAccount;
import backend.banking.SavingsAccount;

public interface Visitor {

    double visit(SavingsAccount savingsAccount);

    double visit(CheckingAccount checkingAccounts);

}
