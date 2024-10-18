package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LedgerScreen {
    static Scanner reader = new Scanner(System.in);
    static LocalTime timeToday = LocalTime.now();
    static LocalDate dateToday = LocalDate.now();
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String date = dateToday.format(df);
    static String time = timeToday.format(tf);


    // displaying everything in the file
    public static void displayAllEntries() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                    System.out.println(input);
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayDeposits() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                // splitting string
                String[] splittingFields = input.split("\\|");
                String date = splittingFields[0];
                String time = splittingFields[1];
                String description = splittingFields[2];
                String vendor = splittingFields[3];
                // parsing from string to double so that I can then only get positive amounts for deposits
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
                // parsing from string to double so that I can then only get negative amounts for payments
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

    public static void displayReports() throws FileNotFoundException {
        String reportOptions;
        // another do while loop, will run unless 0 is selected
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
                    ReportsScreen.displayMonthToDate();
                    break;
                case "2":
                    ReportsScreen.displayPreviousMonth();
                    break;
                case "3":
                    ReportsScreen.displayYearToDate();
                    break;
                case "4":
                    ReportsScreen.displayPreviousYear();
                    break;
                case "5":
                    ReportsScreen.searchVendor();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }
        } while (!reportOptions.equals("0"));
    }
}
