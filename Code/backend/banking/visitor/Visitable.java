package backend.banking.visitor;

public interface Visitable {

    public double accept(Visitor visitor);
}
