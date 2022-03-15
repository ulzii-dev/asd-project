package edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor;

import edu.mum.cs.cs525.labs.exercises.project.backend.commons.CheckingAccounts;
import edu.mum.cs.cs525.labs.exercises.project.backend.commons.SavingsAccount;

public interface Visitor {

    public double visit(SavingsAccount savingsAccount);

    public double visit(CheckingAccounts checkingAccounts);

}
