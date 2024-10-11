package com.pluralsight;
import java.util.Scanner;

public class Main {
    static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Hello! Welcome to the CLI Finance Application. To get started, please select one of the following options: "+
                "\n" + "D - Add Deposit" +
                "\n" + "P - Make a Payment (Debit)" +
                "\n" + "L - Ledger" +
                "\n" + "X - Exit"
        );

        // this is gonna be the home screen
        String homeScreenOptions = "";
        homeScreenOptions = reader.nextLine();
        // setting up a switch statement for the home screen options
        //maybe need a for loop to select other options??
        switch (homeScreenOptions.toUpperCase()) {
           case "D": // prompt for deposit info + save to csv file
               System.out.println("What is your deposit information?");
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
                ;
                break;
            default:
                System.out.println("That is not a valid selection. Please try again.");
        }


       reader.close();
    }
}