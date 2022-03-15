package factory;
import backend.commons.Account;

public abstract class AccountFactory {
    public abstract Account createCheckingAccount();
    public abstract Account createSavingsAccount();
    public abstract Account createCreditCardAccount();
}

