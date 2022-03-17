package edu.miu.cs525.banking.visitor;


import edu.miu.cs525.banking.domain.CheckingAccount;
import edu.miu.cs525.banking.domain.SavingsAccount;
import edu.miu.cs525.creditcard.domain.CreditCardAccount;

public interface Visitor {

    double visit(SavingsAccount savingsAccount);

    double visit(CheckingAccount checkingAccounts);

    double visit(CreditCardAccount creditCardAccount);

}
