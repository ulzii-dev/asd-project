package edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor;

import edu.mum.cs.cs525.labs.exercises.project.backend.banking.CheckingAccounts;
import edu.mum.cs.cs525.labs.exercises.project.backend.banking.SavingsAccount;
import edu.mum.cs.cs525.labs.exercises.project.backend.banking.strategy.CompanySavingsAccountComputation;

public interface Visitor {

    public double visit(SavingsAccount savingsAccount);
    public double visit(CheckingAccounts checkingAccounts);

}
