package model;

import java.io.Serializable;
import java.util.Date;

public class Recovery implements Serializable {
    private int recoveryID;
    private String itemName;
    private double itemPrice;

    private String userName;
    private int recoveryQuantity;
    private double recoveryTotalPrice;
    private Date recoveryDate;

    //constructor
    public Recovery(int recoveryID, String itemName, double itemPrice, String userName, int recoveryQuantity, double recoveryTotalPrice, Date recoveryDate) {
        this.recoveryID = recoveryID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userName = userName;

        this.recoveryQuantity = recoveryQuantity;
        this.recoveryTotalPrice = recoveryTotalPrice;
        this.recoveryDate = recoveryDate;
    }

//getters and setters
    public int getRecoveryID() {
        return recoveryID;
    }

    public void setRecoveryID(int recoveryID) {
        this.recoveryID = recoveryID;
    }


    public int getRecoveryQuantity() {
        return recoveryQuantity;
    }

    public void setRecoveryQuantity(int recoveryQuantity) {
        this.recoveryQuantity = recoveryQuantity;
    }

    public double getRecoveryTotalPrice() {
        return recoveryTotalPrice;
    }

    public void setRecoveryTotalPrice(double recoveryTotalPrice) {
        this.recoveryTotalPrice = recoveryTotalPrice;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItem(String item) {
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
                "\nRecovery ID        : " + recoveryID +
                "\nItem Name          : " + itemName +
                "\nItem Price         : $" + String.format("%.2f", itemPrice) +
                "\nUser Name          : " + userName +
                "\nRecovery Quantity  : " + recoveryQuantity +
                "\nTotal Price        : $" + String.format("%.2f", recoveryTotalPrice) +
                "\nRecovery Date      : " + recoveryDate +
                "\n----------------------------------";
    }


}
