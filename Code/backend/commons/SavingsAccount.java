package edu.mum.cs.cs525.labs.exercises.project.backend.commons;

import edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor.Visitable;
import edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor.Visitor;

public class SavingsAccount extends Account implements Visitable {
    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }


    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String getAccountTypes(){
        return AccountTypes.Saving.name();
    }
}
