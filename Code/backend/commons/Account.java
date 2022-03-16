package backend.commons;

import backend.banking.visitor.InterestComputerVisitor;
import backend.banking.visitor.Visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Account {
    private final List<AccountEntry> accountEntries;
    private final InterestComputationStrategy interestComputationStrategy;
    InterestComputerVisitor visitor = new InterestComputerVisitor();
    private Customer customer;
    private String accountNumber;
    private String accountType;

    public Account(InterestComputationStrategy interestComputationStrategy) {
        this.interestComputationStrategy = interestComputationStrategy;
        this.accountEntries = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : accountEntries) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
        accountEntries.add(entry);
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
        accountEntries.add(entry);
    }

    private void addEntry(AccountEntry entry) {
        accountEntries.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());

        accountEntries.add(fromEntry);

        toAccount.addEntry(toEntry);
    }

    public void addInterest() {
        // will be and can be used interchangebaly with visitor pattern
        //double interestStrategy =  interestComputationStrategy.computeInterest(getBalance());
        double interestVistor = this.accept(visitor);
        AccountEntry entry = new AccountEntry(interestVistor, "interest added", "", "");
        accountEntries.add(entry);

    }

    public abstract double accept(Visitor visitor);

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<AccountEntry> getAccountEntries() {
        return accountEntries;
    }

    public abstract String getAccountType();

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
