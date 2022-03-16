package backend.creditcard.dao;

import backend.commons.Account;
import backend.commons.AccountDAO;

import java.util.ArrayList;
import java.util.Collection;

public class CreditCardAccountDAO implements AccountDAO {
    private static volatile CreditCardAccountDAO instance;
    Collection<Account> accountList = new ArrayList<>();

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
        accountList.add(account);
        System.out.println("Saving account " + account.getAccountNumber() + " for customer " + account.getCustomer().getName());
    }

    @Override
    public void updateAccount(Account account) {
        Account isAccountExist = loadAccount(account.getAccountNumber());
        if (isAccountExist != null) {
            accountList.remove(isAccountExist); // remove the old
            accountList.add(account); // add the new
        }
        System.out.println("Updating account " + account.getAccountNumber() + " for customer " + account.getCustomer().getName());
    }

    @Override
    public Account loadAccount(String accountNumber) {
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    @Override
    public Collection<Account> getAccounts() {
        return accountList;
    }
}
