package model;

import java.io.Serializable;

public class Report implements Serializable {
    private int reportID;
    private String transactionType;
    private String itemName;
    private String itemPrice;
    private String userName;
    private int Quantity;
    private double totalPrice;

    private String transactionDate;




    //constructor
    public Report(int reportID, String transactionType, String itemName, String itemPrice, String userName, int Quantity, double totalPrice, String transactionDate) {
        this.reportID = reportID;
        this.transactionType = transactionType;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userName = userName;
        this.Quantity = Quantity;
        this.totalPrice = totalPrice;
        this.transactionDate = transactionDate;



    }


    // Getters and Setters
    public int getReportID() { return reportID; }
    public void setReportID(int reportID) { this.reportID = reportID; }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
