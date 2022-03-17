package edu.miu.cs525.shared.domain;

import edu.miu.cs525.shared.Account;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Customer {
    private final Set<Account> accountList;
    private int customerId;
    private String name;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String email;

    public Customer(int customerId, String name,
                    String street, String city,
                    String state, int zip,
                    String email) {
        this.customerId = customerId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.accountList = new HashSet<>();
    }

    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    public void removeAccount(Account account) {
        this.accountList.remove(account);
    }

    public Collection<Account> getAccountList() {
        return accountList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
