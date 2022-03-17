package edu.miu.cs525.shared.domain;

import java.time.LocalDate;

public class AccountEntry {
    private LocalDate date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;

    public AccountEntry() {
    }

    //For Generate Report
    //public AccountEntry(double amount, String description, String fromAccountNumber, String fromPersonName, long day) {
    public AccountEntry(double amount, String description, String fromAccountNumber, String fromPersonName) {
        super();
        // For Generate Report
        // this.date = LocalDate.now().plusDays(day);
        this.date = LocalDate.now();
        this.amount = amount;
        this.description = description;
        this.fromAccountNumber = fromAccountNumber;
        this.fromPersonName = fromPersonName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    public String report() {
        return "Amount: " + amount + " Description: " + description;
    }
}
