package com.pluralsight;

public class Transactions {

    // fields for transactions

    private String date;
    private String time;
    private String item;
    private String vendor;
    private double amount;

    // constructors (w/ parameters + empty) :D
    public Transactions(String _date, String _time, String _item, String _vendor, double _amount){
        this.date = _date;
        this.time = _time;
        this.item = _item;
        this.vendor = _vendor;
        this.amount = _amount;
    }

    public Transactions(){

    }

    //getter + setter methods
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
