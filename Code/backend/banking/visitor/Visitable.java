package edu.mum.cs.cs525.labs.exercises.project.backend.banking.visitor;

public interface Visitable {

    public double accept(Visitor visitor);
}
