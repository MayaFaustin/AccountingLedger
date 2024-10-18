package com.pluralsight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class HomeScreen {
    static Scanner reader = new Scanner(System.in);
    static LocalTime timeToday = LocalTime.now();
    static LocalDate dateToday = LocalDate.now();
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String date = dateToday.format(df);
    static String time = timeToday.format(tf);


    public static void makeDeposit() {
        System.out.println("You've selected \"Add Deposit\"" +
                "\n Please enter a description of your deposit: ");
        String description = reader.nextLine();
        System.out.println("Enter the vendor information: ");
        String vendor = reader.nextLine();
        System.out.println("Enter the amount of your deposit: ");
        double amount = reader.nextDouble();

        String transactions = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(transactions);
            bufWriter.newLine();
            System.out.println("Success! A new deposit was added :D");
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void makePayment() {
        System.out.println("You've selected \"Make a Payment\"" +
                "\n" + "Please enter a description of your payment: ");
        String description = reader.nextLine();
        System.out.println("Enter the vendor information: ");
        String vendor = reader.nextLine();
        System.out.println("Enter the amount of your payment: ");
        double amount = reader.nextDouble();
        amount = amount *= -1;
        String transactions = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        try {
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(transactions);
            bufWriter.newLine();
            System.out.println("Success! A new payment was made :D");
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void displayLedgerScreen() throws IOException {

        String ledgerScreenOptions = "";
        do {
            System.out.println("Welcome to the ledger screen. Please select one of the following options: " +
                    "\n" + "A - All" +
                    "\n" + "D - Deposits" +
                    "\n" + "P - Payments" +
                    "\n" + "R - Reports" +
                    "\n" + "H - Home");

            // for ledger options
            // going to call method inside home screen method :D
            ledgerScreenOptions = reader.nextLine();
            switch (ledgerScreenOptions.toUpperCase()) {
                case "A": // display all entries (displaying all objects, is an array needed?)
                    LedgerScreen.displayAllEntries();
                    break;
                case "D":
                    LedgerScreen.displayDeposits();
                    break;
                case "P":
                    LedgerScreen.displayPayments();
                    break;
                case "R": // whole new screen to show reports :D
                    LedgerScreen.displayReports();
                    break;
                case "H": // to go back to home screen
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }
        } while (!ledgerScreenOptions.equalsIgnoreCase("h"));
    }

}
