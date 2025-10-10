package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(double amount, String vendor, String description, LocalTime time, LocalDate date) {
        this.amount = amount;
        this.vendor = vendor;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
    public String toCSV(){
        return date + "|" + time + "|"+ description + "|" + amount;
    }
    public static Transaction fromCSV(String csvLine) {
        String [] tokens = csvLine.split("\\|");

        LocalDate date = LocalDate.parse(tokens[0]);
        LocalTime time = LocalTime.parse(tokens[1]);
        String description = tokens[2];
        String vendor = tokens[3];
        double amount = Double.parseDouble(tokens[4]);


        return new Transaction(amount, vendor, description, time, date);
    }


}
