package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {

    public static Scanner input = null;

    public static void main(String[] args) {
        input = new Scanner(System.in);
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

        String choice = input.nextLine().trim().toUpperCase();

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

            String selection = input.nextLine().trim().toUpperCase();

            switch (selection) {

                case "A":
                    //TODO: Create dAE method
                    displayAllEntries();
                    break;

                case "D":
                    //TODO ; Create dD method
                    displayDeposits();
                    break;

                case "P":
                    //TODO create dP method
                    displayPayments();
                    break;

                case "R":
                    //TODO Create sRS method
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
    private static void saveTransaction(Transaction transaction) {
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("transactions.csv",true));

            bufWriter.write(transaction.toCSV());

            bufWriter.newLine();

            bufWriter.close();


        } catch (IOException e) {
            System.out.println("Error saving transaction");

        }

    }
}
