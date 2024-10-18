package com.pluralsight;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        displayHomeScreen();
    }

    // while loop to keep home screen running unless x is selected
    public static void displayHomeScreen() throws IOException {
        String homeScreenOptions = "";
        // switch statement to select home screen options
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
                    HomeScreen.makeDeposit();
                    break;
                case "P": // prompt for debit info + save to csv file
                    HomeScreen.makePayment();
                    break;
                case "L": // this is gonna display the ledger screen
                    HomeScreen.displayLedgerScreen();
                    break;
                case "X": // to exit the program
                    System.out.println("Thank you for visiting the CLI Finance Application. Goodbye...");
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }
        }
    }
}