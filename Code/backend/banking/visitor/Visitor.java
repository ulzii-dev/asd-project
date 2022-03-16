package backend.banking.visitor;

import backend.banking.domain.CheckingAccount;
import backend.banking.domain.SavingsAccount;

public interface Visitor {

    double visit(SavingsAccount savingsAccount);

    double visit(CheckingAccount checkingAccounts);

}
