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

    public static void main(String[] args) {

        System.out.println("Hello! Welcome to the CLI Finance Application." +
                "\n To get started, please select one of the following options: " +
                "\n D - Add Deposit" +
                "\n P - Make a Payment (Debit)" +
                "\n L - Ledger" +
                "\n X - Exit"
        );
        displayHomeScreen();

    }

    //while loop to keep home screen running unless x is selected
    public static void displayHomeScreen(){
            String homeScreenOptions = "";

            // setting up a switch statement for the home screen options
          while (!homeScreenOptions.equalsIgnoreCase("x")) {
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
            allTransactions.add(new Transactions("","","","",0));
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
//                + "\n Please enter your four digit pin:
//        int pin;
//        pin = reader.nextInt();
//        reader.nextLine(); // eating the line
        // why does it continue after entering the wrong pin?
        //was trying to create a pin and do some validation, gonna come back to it later
//        while(pin == 2001){
//            System.out.println("Welcome! Please enter a description of your payment: ");
//        }if(pin != 2001){
//            System.out.println("That is incorrect. Please try again.");
//        }
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
            allTransactions.add(new Transactions("","","","",0));
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
    public static void displayLedgerScreen() {

            // displaying selection options to user
            System.out.println("Welcome to the ledger screen. Please select one of the following options: " +
                    "\n" + "A - All" +
                    "\n" + "D - Deposits" +
                    "\n" + "P - Payments" +
                    "\n" + "R - Reports" +
                    "\n" + "H - Home"
            );

            // for ledger options
            // going to call method inside home screen method :D
            String ledgerScreenOptions = "";
            ledgerScreenOptions = reader.nextLine();
            switch (ledgerScreenOptions.toUpperCase()) {
                case "A": // display all entries (displaying all objects, is an array needed?)
                    displayAllEnteries();
                    break;
                case "D": // only display deposits into the account

                    reader.nextLine();
                    break;
                case "P": // display only negative entries/ payments (if statement)

                    break;
                case "R": // whole new screen to show reports :D
                    ;
                    break;
                case "H": // to go back to home screen

                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }

        }

    public static void displayAllEnteries() {
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
}
