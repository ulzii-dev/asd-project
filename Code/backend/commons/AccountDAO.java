package edu.mum.cs.cs525.labs.exercises.project.backend.commons;

import java.util.Collection;

public interface AccountDAO {
    void createAccount(Account account);
    void updateAccount(Account account);
    Account loadAccount(String accountNumber);
    Collection<Account> getAccounts();
}
