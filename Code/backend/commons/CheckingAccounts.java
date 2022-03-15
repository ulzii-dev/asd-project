package backend.commons;

import backend.banking.visitor.Visitable;
import backend.banking.visitor.Visitor;

public class CheckingAccounts extends Account {
    public CheckingAccounts(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getAccountType() {
        return null;
    }

    public String getAccountTypes(){
        return AccountType.Checking.name();
    }
}
