package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;
import java.time.format.DateTimeFormatter;

public class Main {
    static Scanner reader = new Scanner(System.in);
    static LocalTime timeToday = LocalTime.now();
    static LocalDate dateToday = LocalDate.now();
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String date = dateToday.format(df);
    static String time = timeToday.format(tf);
    static ArrayList<Transactions> allTransactions = new ArrayList<Transactions>();

    public static void main(String[] args) throws IOException {
        displayHomeScreen();
    }

    //while loop to keep home screen running unless x is selected
    public static void displayHomeScreen() throws IOException {
            String homeScreenOptions = "";
            // setting up a switch statement for the home screen options
          while (!homeScreenOptions.equalsIgnoreCase("x")) {
              System.out.println("Hello! Welcome to the CLI Finance Application." +
                      "\n To get started, please select one of the following options: " +
                      "\n D - Add Deposit" +
                      "\n P - Make a Payment (Debit)" +
                      "\n L - Ledger" +
                      "\n X - Exit"
              );
              homeScreenOptions = reader.nextLine();
                switch (homeScreenOptions.toUpperCase()) {
                    case "D": // prompt for deposit info + save to csv file
                        makeDeposit();
                        break;
                    case "P": // prompt for debit info + save to csv file
                        makePayment();
                        break;
                    case "L": // this is gonna display the ledger screen
                        displayLedgerScreen();
                        break;
                    case "X": // to exit the program
                        System.out.println("Thank you for visiting the CLI Finance Application. Goodbye...");
                        break;
                    default:
                        System.out.println("That is not a valid selection. Please try again.");
                }
            }

        }

    public static void makeDeposit(){
        System.out.println("You've selected \"Add Deposit\"" +
                "\n Please enter a description of your deposit: ");
        String description = reader.nextLine();
        System.out.println("Enter the vendor information: ");
        String vendor = reader.nextLine();
        System.out.println("Enter the amount of your deposit: ");
        double amount = reader.nextDouble();

        String transactions = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        try {
            // open the file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // write to the file
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(transactions);
            bufWriter.newLine();
           // allTransactions.add(new Transactions("","","","",0));
            System.out.println("Success! A new deposit was added :D");
            bufWriter.close();
        }
        catch (IOException e) {
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
            // open the file
            //appended so that file isn't being constantly written over
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            // write to the file
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(transactions);
            bufWriter.newLine();
           // allTransactions.add(new Transactions("","","","",0));
            System.out.println("Success! A new payment was made :D");
            bufWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        //currently trying to get out of payments if selecting no
    }
        // (incomplete) creating method for ledger screen
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
                        displayAllEntries();
                        break;
                    case "D":
                        displayDeposits();
                        break;
                    case "P":
                        displayPayments();
                        break;
                    case "R": // whole new screen to show reports :D
                        displayReports();
                        break;
                    case "H": // to go back to home screen
                        break;
                    default:
                        System.out.println("That is not a valid selection. Please try again.");
                }
            } while(!ledgerScreenOptions.equalsIgnoreCase("h"));
        }

    public static void displayAllEntries() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while((input = bufReader.readLine()) != null) {
                System.out.println(input);
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayDeposits(){

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while((input = bufReader.readLine()) != null) {
              String[] splittingFields = input.split("\\|");
              String date = splittingFields[0];
              String time = splittingFields[1];
              String description = splittingFields[2];
              String vendor = splittingFields[3];
              double amount = Double.parseDouble(splittingFields[4]);
              Transactions t = new Transactions(date, time, description, vendor, amount);
                if (t.getAmount() > 0) {
                    System.out.println(input);
                }
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayPayments() throws IOException {
        try {

            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                //splitting the fields to get amount
                String[] splittingFields = input.split("\\|");
                String date = splittingFields[0];
                String time = splittingFields[1];
                String description = splittingFields[2];
                String vendor = splittingFields[3];
                double amount = Double.parseDouble(splittingFields[4]);
                Transactions t = new Transactions(date, time, description, vendor, amount);
                if (t.getAmount() < 0) {
                    System.out.println(input);
                }
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayReports() {
        String reportOptions;
        do {

            System.out.println("Welcome to your reports. Please select one of the following options: " +
                    "\n" + "1 - Month to Date" +
                    "\n" + "2 - Previous Month" +
                    "\n" + "3 - Year to Date" +
                    "\n" + "4 - Previous Year" +
                    "\n" + "5 - Search by Vendor" +
                    "\n" + "0 - Back");
            reportOptions = reader.nextLine();
           // reader.nextLine();
            switch (reportOptions) {
                case "1":
                    System.out.println("testing1");
                    break;
                case "2":
                    System.out.println("testing2");
                    break;
                case "3":
                    System.out.println("testing3");
                    break;
                case "4":
                    System.out.println("testing4");
                    break;
                case "5":
                    System.out.println("testing5");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }
        } while (!reportOptions.equals("0"));
    }
    public static void displayMonthToDate(){

    }
}
