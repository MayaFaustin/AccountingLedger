package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReportsScreen {
    static Scanner reader = new Scanner(System.in);
    static LocalTime timeToday = LocalTime.now();
    static LocalDate dateToday = LocalDate.now();
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String date = dateToday.format(df);
    static String time = timeToday.format(tf);
    public static void displayReports() throws FileNotFoundException {
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
                    displayMonthToDate();
                    break;
                case "2":
                    displayPreviousMonth();
                    break;
                case "3":
                    displayYearToDate();
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    searchVendor();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("That is not a valid selection. Please try again.");
            }
        } while (!reportOptions.equals("0"));

    }

    public static void searchVendor(){
        System.out.println("Please enter the vendor here:");
        String vendorSearch = reader.nextLine();
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                //splitting the fields to get amount
                String[] splittingFields = input.split("\\|");
                String vendor = splittingFields[3];
                if (vendor.equalsIgnoreCase(vendorSearch)) {
                    System.out.println(input);
                }
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayMonthToDate() throws FileNotFoundException {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            while((input = bufReader.readLine()) != null) {
                String[] splittingFields = input.split("\\|");
                String splitDate = splittingFields[0];
                LocalDate date = LocalDate.parse(splitDate, df);
                if ((date.getMonth() == LocalDate.now().getMonth() && date.getYear() == LocalDate.now().getYear())) {
                    System.out.println(input);
                }
            }
            bufReader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayPreviousMonth(){
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            LocalDate previousMonth = LocalDate.now().minusMonths(1);
            while((input = bufReader.readLine()) != null) {
                String[] splittingFields = input.split("\\|");
                String splitDate = splittingFields[0];
                LocalDate date = LocalDate.parse(splitDate, df);
                if ((date.getMonth() == previousMonth.getMonth() && date.getYear() == previousMonth.getYear())) {
                    System.out.println(input);
                }
            }
            bufReader.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayYearToDate(){
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            LocalDate Year = LocalDate.now();
            while((input = bufReader.readLine()) != null) {
                String[] splittingFields = input.split("\\|");
                String splitDate = splittingFields[0];
                LocalDate date = LocalDate.parse(splitDate, df);
                if ((date.getYear() == Year.getYear())) {
                    System.out.println(input);
                }
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayPreviousYear(){
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.csv"));
            String input = bufReader.readLine();
            LocalDate previousYear = LocalDate.now().minusYears(1);
            while((input = bufReader.readLine()) != null) {
                String[] splittingFields = input.split("\\|");
                String splitDate = splittingFields[0];
                LocalDate date = LocalDate.parse(splitDate, df);
                if ((date.getYear() == previousYear.getYear())) {
                    System.out.println(input);
                }
            }
            bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
