package backend.banking.builder;

import backend.banking.builder.AccountBuilder;
import backend.commons.Customer;

public class AccountData {
    private String accountNumber;
    private String accountType;
    private Customer customer;

    public static AccountBuilder builder(){
        return new AccountBuilder();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", customer=" + customer +
                '}';
    }
}
