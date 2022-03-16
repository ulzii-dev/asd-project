package backend.banking.dao;

import backend.commons.Account;
import backend.commons.AccountDAO;
import backend.commons.Log;

import java.util.ArrayList;
import java.util.Collection;

public class BankingAccountDAO implements AccountDAO {
    private static volatile BankingAccountDAO instance;
    Collection<Account> accountlist = new ArrayList<>();

    public static BankingAccountDAO getInstance() {
        if (instance == null) {
            synchronized (BankingAccountDAO.class) {
                if (instance == null) {
                    instance = new BankingAccountDAO();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Account account) {
        accountlist.add(account);
        Log.getLogger().write("Creating account with AccountNumber: " + account.getAccountNumber() +
                ", CustomerName: " +
                account.getCustomer().getName());
    }

    @Override
    public void updateAccount(Account account) {
        Account isAccountExist = loadAccount(account.getAccountNumber());
        if (isAccountExist != null) {
            accountlist.remove(isAccountExist); // remove the old
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
