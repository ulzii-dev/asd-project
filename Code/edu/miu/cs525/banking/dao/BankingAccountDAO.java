package edu.miu.cs525.banking.dao;

import edu.miu.cs525.commons.Account;
import edu.miu.cs525.commons.AccountDAO;
import edu.miu.cs525.commons.Log;

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
        Log.getLogger().write("Account Created with AccountNumber: " + account.getAccountNumber() +
                ", CustomerName: " +
                account.getCustomer().getName());
    }

    @Override
    public void update(Account account) {
        Account isAccountExist = getAccountByAccountNumber(account.getAccountNumber());
        if (isAccountExist != null) {
            accountlist.remove(isAccountExist); // remove the old
            accountlist.add(account); // add the new
        }
        Log.getLogger().write("Updating account " + account.getAccountNumber() + " for customer " + account.getCustomer().getName());
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        for (Account account : accountlist) {
            if (account.getAccountNumber().equals(accountNumber)) {
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
