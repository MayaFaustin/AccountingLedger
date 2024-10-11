package com.pluralsight;

public class Transactions {

    // fields
    //do i need date + time as well?
    private String item;
    private String vendor;
    private double amount;

    // constructors (w/ parameters + empty) :D
    public Transactions(String _item, String _vendor, double _amount){
        this.item = _item;
        this.vendor = _vendor;
        this.amount = _amount;
    }

    public Transactions(){

    }

    //getter + setter methods


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
