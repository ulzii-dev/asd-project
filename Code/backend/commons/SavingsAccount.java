package edu.mum.cs.cs525.labs.exercises.project.backend.commons;
import edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor.*;

public class SavingsAccount extends Account implements Visitable{
    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }


    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
