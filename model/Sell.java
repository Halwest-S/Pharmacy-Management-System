package model;

import java.io.Serializable;
import java.util.Date;

public class Sell implements Serializable {
    private static final long serialVersionUID = 7661025235606081511L;
    private int sellID;
    private String itemName;
    private double itemPrice;
    private String userName; // Keep this as a User object
    private int sellQuantity;
    private double sellTotalPrice;
    private Date sellDate;

    // Constructor
    public Sell(int sellID, String itemName, double itemPrice, String userName, int sellQuantity, double sellTotalPrice, Date sellDate) {
        this.sellID = sellID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userName = userName;
        this.sellQuantity = sellQuantity;
        this.sellTotalPrice = sellTotalPrice;
        this.sellDate = sellDate;
    }

    // Getters and setters
    public int getSellID() {
        return sellID;
    }

    public void setSellID(int sellID) {
        this.sellID = sellID;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(int sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getSellTotalPrice() {
        return sellTotalPrice;
    }

    public void setSellTotalPrice(double sellTotalPrice) {
        this.sellTotalPrice = sellTotalPrice;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public String getUserName() {  // Return the User object
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return
                "\nSell ID           : " + sellID +
                "\nItem Name         : " + itemName +
                "\nItem Price        : $" + String.format("%.2f", itemPrice) +
                "\nUser Name         : " + userName +
                "\nQuantity Sold     : " + sellQuantity +
                "\nTotal Price       : $" + String.format("%.2f", sellTotalPrice) +
                "\nDate of Sale      : " + sellDate +
                "\n**********************************";
    }


}


