package controller;

import DAO.SellDAO;
import Util.FileUtil;
import model.Item;
import model.Sell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class sellController {
    private SellDAO sellDAO;

    public sellController() {
        sellDAO = new SellDAO();  // Initialize SellDAO for database operations
    }

    // Add a new sell using the database
    public String addSell(Sell sell) {
        if (sellDAO.getSellById(sell.getSellID()) == null) {
            sellDAO.addSell(sell);
            return "Sale added successfully.";
        } else {
            return "Sale with ID " + sell.getSellID() + " already exists.";
        }
    }

    // Remove a sell from the database
    public String removeSell(int sellID) {
        sellDAO.removeSell(sellID);
        return "Sale removed successfully.";
    }

    // Get a sell by ID from the database
    public Sell getSellById(int sellID) {
        return sellDAO.getSellById(sellID);
    }

    // Retrieve all sells from the database
    public ArrayList<Sell> getAllSells() {
        return sellDAO.getAllSells();
    }

    // Update a sell's details in the database
    public String updateSell(int sellID, String itemName, double itemPrice, String userName, int sellQuantity, double sellTotalPrice, Date sellDate) {
        Sell sell = sellDAO.getSellById(sellID);
        if (sell != null) {
            // Update the fields of the sell object
            sell.setItemName(itemName);
            sell.setItemPrice(itemPrice);
            sell.setUserName(userName);
            sell.setSellQuantity(sellQuantity);
            sell.setSellTotalPrice(sellTotalPrice);
            sell.setSellDate(sellDate);

            // Update in the DAO
            sellDAO.updateSell(sell);
            return "Sale updated successfully.";
        } else {
            return "Sale with ID " + sellID + " not found.";
        }
    }
}
