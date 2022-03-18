package edu.miu.cs525.banking.visitor;

public interface Visitable {

    double accept(Visitor visitor);
}
