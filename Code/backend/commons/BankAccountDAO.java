package backend.commons;

import java.util.Collection;

public class BankAccountDAO implements AccountDAO {
    private static volatile BankAccountDAO instance;

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
        Log.getLogger().write("Log instance is working here !!!");
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
