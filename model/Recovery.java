package model;

import java.io.Serializable;

public class Recovery implements Serializable {
    private int recoveryID;
    private String itemName;
    private String itemPrice;

    private String userName;
    private int recoveryQuantity;
    private double recoveryTotalPrice;
    private String recoveryDate;

    //constructor
    public Recovery(int recoveryID, String itemName, String itemPrice, String userName, int recoveryQuantity, double recoveryTotalPrice, String recoveryDate) {
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

    public String getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(String recoveryDate) {
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

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return "Recovery ( " +
                "recoveryID: " + recoveryID +
                ", itemName: " + itemName +
                ", itemPrice: " + itemPrice +
                ", userName: " + userName +
                ", recoveryQuantity: " + recoveryQuantity +
                ", recoveryTotalPrice: " + recoveryTotalPrice +
                ", recoveryDate: " + recoveryDate +
                ')';
    }

}
