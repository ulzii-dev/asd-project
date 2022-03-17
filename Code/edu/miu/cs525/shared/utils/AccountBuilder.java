package edu.miu.cs525.shared.utils;

import edu.miu.cs525.shared.dto.AccountDTO;
import edu.miu.cs525.shared.domain.Customer;

public class AccountBuilder {
    private String accountNumber;
    private String accountType;
    private Customer customer;

    public AccountBuilder(){}

    public AccountBuilder(AccountDTO accountDTO){
        this.accountNumber = accountDTO.getAccountNumber();
        this.accountType = accountDTO.getAccountType();
        this.customer = accountDTO.getCustomer();
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

    public AccountDTO build(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountType(accountType);
        accountDTO.setAccountNumber(accountNumber);
        accountDTO.setCustomer(customer);
        return accountDTO;
    }
}
