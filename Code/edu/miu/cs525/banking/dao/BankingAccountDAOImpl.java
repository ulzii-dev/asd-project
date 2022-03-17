package edu.miu.cs525.banking.dao;

import edu.miu.cs525.shared.Account;
import edu.miu.cs525.shared.dao.AccountDAO;
import edu.miu.cs525.shared.Log;

import java.util.ArrayList;
import java.util.Collection;

public class BankingAccountDAOImpl implements AccountDAO {
    private static volatile BankingAccountDAOImpl instance;
    Collection<Account> accountlist = new ArrayList<>();

    public static BankingAccountDAOImpl getInstance() {
        if (instance == null) {
            synchronized (BankingAccountDAOImpl.class) {
                if (instance == null) {
                    instance = new BankingAccountDAOImpl();
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
            accountlist.remove(isAccountExist); // REMOVES THE OLD
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
