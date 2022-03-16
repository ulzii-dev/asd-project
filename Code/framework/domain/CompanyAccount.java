package framework.domain;

import backend.commons.Customer;

public class CompanyAccount extends Customer {
    int numOfEmployee;
    public CompanyAccount(int customerId, String name,
                          String street, String city, String state,
                          int zip, String email,int numOfEmployee) {
        super(customerId, name, street, city, state, zip, email);
        this.numOfEmployee = numOfEmployee;

    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }
}
