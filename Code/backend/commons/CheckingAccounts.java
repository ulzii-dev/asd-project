package edu.mum.cs.cs525.labs.exercises.project.backend.commons;

import edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor.Visitable;
import edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor.Visitor;

public class CheckingAccounts extends Account implements Visitable {
    public CheckingAccounts(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
