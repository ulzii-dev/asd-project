package edu.miu.cs525.commons;

import java.time.LocalDate;

public class Personal extends Customer {
    private LocalDate birthDate;

    public Personal(int id, String name, String street, String city, String state, int zip, String email, LocalDate birthDate) {
        super(id, name, street, city, state, zip, email);
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
