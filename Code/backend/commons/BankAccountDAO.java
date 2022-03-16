package backend.commons;

import java.util.ArrayList;
import java.util.Collection;

public class BankAccountDAO implements AccountDAO {
    private static volatile BankAccountDAO instance;
    Collection<Account> accountlist = new ArrayList<>();

    public static BankAccountDAO getInstance() {
        if (instance == null) {
            synchronized (BankAccountDAO.class) {
                if (instance == null) {
                    instance = new BankAccountDAO();
                }
            }
        }

        return instance;
    }

    @Override
    public void createAccount(Account account) {
        accountlist.add(account);
        Log.getLogger().write("Creating account: " + account.getAccountNumber());
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public Account loadAccount(String accountNumber) {
        return null;
    }

    @Override
    public Collection<Account> getAccounts() {
        return null;
    }
}
