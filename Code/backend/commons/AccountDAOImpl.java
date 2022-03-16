package backend.commons;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDAOImpl implements AccountDAO {
    Collection<Account> accounts;

    public AccountDAOImpl(){
        accounts = new ArrayList<Account>();
    }

    public void createAccount(Account account) {
        accounts.add(account);
    }

    public void updateAccount(Account account) {
        Account isAccountExist = loadAccount(account.getAccountNumber());
        if (isAccountExist != null) {
            accounts.remove(isAccountExist);
            accounts.add(account);
        }

    }

    public Account loadAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

}
