package backend.banking.dto;

import backend.commons.Customer;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    private final String accountType;
    private final String accountNumber;
    private final Customer customer;

    public AccountDTO(String accountType, String accountNumber, Customer customer){
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.customer = customer;
    }

    public String getAccountType(){
        return accountType;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public Customer getCustomer(){
        return customer;
    }
}
