package backend.commons;

public class Company extends Customer {
    int numOfEmployees;

    public Company(int id, String name, String street, String city, String state, int zip, String email, int numOfEmployees) {
        super(id, name, street, city, state, zip, email);
        this.numOfEmployees = numOfEmployees;
    }

    public int getNumOfEmployee() {
        return numOfEmployees;
    }

    public void setNumOfEmployee(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}

