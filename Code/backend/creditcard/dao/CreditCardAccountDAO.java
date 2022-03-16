package backend.creditcard.dao;

import backend.commons.Account;
import backend.commons.AccountDAO;
import backend.commons.Log;

import java.util.ArrayList;
import java.util.Collection;

public class CreditCardAccountDAO implements AccountDAO {
    private static volatile CreditCardAccountDAO instance;
    Collection<Account> accountlist = new ArrayList<>();

    public static CreditCardAccountDAO getInstance() {
        if (instance == null) {
            synchronized (CreditCardAccountDAO.class) {
                if (instance == null) {
                    instance = new CreditCardAccountDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Account account) {
        accountlist.add(account);
        Log.getLogger().write("Saving account " + account.getAccountNumber() + " for customer " + account.getCustomer().getName());
    }

    @Override
    public void updateAccount(Account account) {
        Account accountexist = loadAccount(account.getAccountNumber());
        if (accountexist != null) {
            accountlist.remove(accountexist); // remove the old
            accountlist.add(account); // add the new
        }
        Log.getLogger().write("Updating account " + account.getAccountNumber() + " for customer " + account.getCustomer().getName());
    }

    @Override
    public Account loadAccount(String accountNumber) {
        for (Account account : accountlist) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    @Override
    public Collection<Account> getAccounts() {
        return accountlist;
    }
}
