package com.pluralsight;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        // formatting for date + time
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Hello! Welcome to the CLI Finance Application. To get started, please select one of the following options: " +
                "\n" + "D - Add Deposit" +
                "\n" + "P - Make a Payment (Debit)" +
                "\n" + "L - Ledger" +
                "\n" + "X - Exit"
        );

        //creating file to write to
        try {
            FileWriter writer = new FileWriter("transactions.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        // this is gonna be the home screen

    // (incomplete) creating a method to display the home screen
    public static void displayHomeScreen(){
            String homeScreenOptions = "";
            homeScreenOptions = reader.nextLine();
            // setting up a switch statement for the home screen options
            //maybe need a for loop to select other options??
            while () {
                switch (homeScreenOptions.toUpperCase()) {

                    // was going to include date + time info in final method? when writing to file, so i was declaring the variables here
                    // String date = (DateTimeFormatter.ofPattern("yyyy-MM-dd").toString());
                    // String time = (DateTimeFormatter.ofPattern("HH:mm:ss").toString());

                    //date|time|description|vendor|amount
                    case "D": // prompt for deposit info + save to csv file
                        System.out.println("You've selected \"Add Deposit\"" +
                                "\n" + "Please enter a description of your deposit: ");
                        String description = reader.nextLine();
                        System.out.println("Enter the vendor information: ");
                        String vendor = reader.nextLine();
                        System.out.println("Enter the amount of your deposit: ");
                        Double depositAmount = reader.nextDouble();
                        //having to store inputs in array
                        break;
                    case "P": // prompt for debit info + save to csv file
                        System.out.println("What is your debit card number?");
                        int debitInfo = reader.nextInt();
                        reader.nextLine();
                        break;
                    case "L": // this is gonna display the ledger screen
                        System.out.println("ahhhhhh");
                        break;
                    case "X": // to exit the program
                        System.out.println("Thank you for visiting the CLI Finance Application. Goodbye...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("That is not a valid selection. Please try again.");
                }
            }
        }

        // (incomplete) creating method for ledger screen
        public static void ledgerScreen() {

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

            reader.close();
        }
}