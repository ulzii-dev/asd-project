package edu.miu.cs525.shared.builder;

import edu.miu.cs525.shared.domain.Customer;

public class AccountBuilder {
    private String accountNumber;
    private String accountType;
    private Customer customer;

    public AccountBuilder(){}

    public AccountBuilder(AccountData accountData){
        this.accountNumber = accountData.getAccountNumber();
        this.accountType = accountData.getAccountType();
        this.customer = accountData.getCustomer();
    }

    public AccountBuilder accountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder accountType(String accountType){
        this.accountType = accountType;
        return this;
    }

    public AccountBuilder customer(Customer customer){
        this.customer = customer;
        return this;
    }

    public AccountData build(){
        AccountData accountData = new AccountData();
        accountData.setAccountType(accountType);
        accountData.setAccountNumber(accountNumber);
        accountData.setCustomer(customer);
        return accountData;
    }
}
