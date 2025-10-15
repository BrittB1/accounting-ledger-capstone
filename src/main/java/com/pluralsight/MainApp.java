package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

    public static Scanner keyboard = null;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        boolean running = true;


        System.out.println("Welcome!");


        while (running) {
            running = showHomeScreen();
        }
    }

    private static boolean showHomeScreen() {

        System.out.println("""
                
                                ==================================
                                          HOME MENU\s
                                ==================================
                                Choose an option by letter:
                               \s
                                D. Add Deposit\s
                                P. Make Payment
                                L. Ledger
                                X. Exit \
                """);

        String choice = keyboard.nextLine().trim().toUpperCase();

        switch (choice) {

            case "D":
                addDeposit();

                break;

            case "P":
                makePayment();
                break;

            case "L":
                showLedgerMenu();
                break;

            case "X":
                System.out.println("Thank you for using the accounting ledger. Come again soon");
                return false;
            default:
                System.out.println("Sorry, invalid choice. Please try again.");


        }
        return true;
    }

    private static void showLedgerMenu() {
        boolean inLedger = true;

        while (inLedger) {

            System.out.println("""
                    
                                    ==================================
                                              LEDGER MENU\s
                                    ==================================
                                    Choose an option by letter:
                                   \s
                                    A. All\s
                                    D. Deposits
                                    P. Payments
                                    R. Reports \s
                                    H. Home
                    """);

            String selection = keyboard.nextLine().trim().toUpperCase();

            switch (selection) {

                case "A":
                    displayAllEntries();
                    break;

                case "D":
                    displayDeposits();
                    break;

                case "P":
                    displayPayments();
                    break;

                case "R":
                    showReportsScreen();
                    break;

                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Sorry invalid option. Please try again");

            }
        }
    }

    // TODO: Implement logic for Ledger Menu
    private static void displayPayments() {
    }

    private static void displayDeposits() {
    }

    private static void displayAllEntries() {
    }

    private static void showReportsScreen() {
        boolean inReports = true;

        while (inReports) {

            System.out.println("""
                    
                                    ==================================
                                              REPORTS MENU\s
                                    ==================================
                                    Choose an option by letter:
                                   \s
                                    1). Month to Date\s
                                    2). Previous Month
                                    3). Year To Date
                                    4). Previous Year\s
                                    5). Search By Vendor
                                    0). Back
                    """);
            String choice = keyboard.nextLine();

            switch (choice) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    inReports = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //TODO: Implement logic for Reports Screen
    private static void searchByVendor() {
        ArrayList<Transaction> transactions = loadTransactions();
    }

    private static void previousYear() {
        ArrayList<Transaction> transactions = loadTransactions();
    }

    private static void yearToDate() {
        ArrayList<Transaction> transactions = loadTransactions();

        int currentYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(currentYear, 1, 1);
        LocalDate endDate = LocalDate.now();

        System.out.println("\n================= + YEAR TO DATE + ===================");
        System.out.println("From " + startDate + " to " + endDate);
        System.out.println(" Date       | Time     | Vendor    | Description          |  Amount  ");
        System.out.println("------------|----------|-----------|----------------------|-----------");

        boolean found = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();

            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                System.out.printf("%-10s | %-8s | %-9s | %-20s | $%8.2f%n", transaction.getDate(), transaction.getTime(),
                        transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
                found = true;

            }
        }
        if (!found) {
            System.out.println("No transactions found");
        }

    }

    private static void previousMonth() {
        ArrayList<Transaction> transactions = loadTransactions();

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        LocalDate startDate = oneMonthAgo.withDayOfMonth(1);
        LocalDate endDate = oneMonthAgo.withDayOfMonth(oneMonthAgo.lengthOfMonth());

        System.out.println("\n================= + PREVIOUS MONTH + ===================");
        System.out.println("From " + startDate + " to " + endDate);
        System.out.println(" Date       | Time     | Vendor    | Description          |  Amount  ");
        System.out.println("------------|----------|-----------|----------------------|-----------");

        boolean found = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionsDate = transaction.getDate();

            if (!transactionsDate.isBefore(startDate) && !transactionsDate.isAfter(endDate)) {
                System.out.printf("%-10s | %-8s | %-9s | %-20s | $%8.2f%n", transaction.getDate(), transaction.getTime(),
                        transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for the previous month.");
        }
    }
    private static void monthToDate() {
        ArrayList<Transaction> transactions = loadTransactions();

        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        System.out.println("\n================= + MONTH TO DATE + ===================");
        System.out.println("From+ " + startOfMonth + " to " + today);
        System.out.println(" Date       | Time     | Vendor    | Description          |  Amount  ");
        System.out.println("------------|----------|-----------|----------------------|-----------");

        boolean found = false;

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();

            if (!transactionDate.isBefore(startOfMonth) && !transactionDate.isAfter(today)) {
                System.out.printf("%-10s | %-8s | %-9s | %-20s | $%8.2f%n", transaction.getDate(), transaction.getTime(),
                        transaction.getVendor(), transaction.getDescription(), transaction.getAmount());

                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for this month.");
        }
        System.out.println("====================================================\n");
    }

    private static void addDeposit() {
        System.out.println("ADD DEPOSIT");

        System.out.print("Enter description: ");
        String description = keyboard.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = keyboard.nextLine();

        System.out.print("Enter amount: ");
        double amount = keyboard.nextDouble();
        keyboard.nextLine();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction transaction = new Transaction(amount, vendor, description, time, date);

        saveTransaction(transaction);

        System.out.println("Deposit Successful");

    }

    private static void makePayment() {
        System.out.println("MAKE PAYMENT");

        System.out.println("Enter vendor: ");
        String vendor = keyboard.nextLine();

        System.out.println("Enter description: ");
        String description = keyboard.nextLine();

        System.out.println("Enter amount: ");
        double amount = keyboard.nextDouble();
        keyboard.nextLine();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        Transaction transaction = new Transaction(-amount, vendor, description, time, date);

        saveTransaction(transaction);

        System.out.println("Payment successful");
    }

    private static void saveTransaction(Transaction transaction) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

            bufWriter.write(transaction.toCSV());

            bufWriter.newLine();

            bufWriter.close();


        } catch (IOException e) {
            System.out.println("Error saving transaction" + e.getMessage());
        }

    }

    private static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));

            String line;
            while ((line = bufReader.readLine()) != null) {
                Transaction transaction = Transaction.fromCSV(line);
                transactions.add(transaction);
            }
            bufReader.close();

        } catch (Exception e) {
            System.out.println("Error loading transactions" + e.getMessage());
        }

        return transactions;
    }
}