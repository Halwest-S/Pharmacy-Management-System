package controller;

import Util.FileUtil;
import model.Item;
import model.Sell;

import java.util.ArrayList;
import java.util.Date;

public class sellController {
    private static ArrayList<Sell> sellsList;
    private static final String FILE_NAME = "sales.txt";



    private static void loadSells() {
        sellsList = (ArrayList<Sell>) FileUtil.readFromFile(FILE_NAME);
        if (sellsList == null) {

            sellsList = new ArrayList<>();

        }
    }

    private static void saveSells() {
        FileUtil.writeToFile(FILE_NAME, sellsList);
    }


    // Add a new sell
    public static void addSell(Sell sell) {
        if (getSellById(sell.getSellID()) == null) {
            sellsList.add(sell);
            saveSells();
        } else {
            System.out.println("Employee with ID " + sell.getSellID() + " already exists.");
        }
    }

    // Remove a sale by ID
    public static void removeSell(int id) {
        sellsList.removeIf(sell -> sell.getSellID() == id);
        saveSells();
    }

    // Get a sale by ID
    public static Sell getSellById(int id) {
        for (Sell sell : sellsList) {
            if (sell.getSellID() == id) {
                return sell;
            }
        }
        return null;
    }

    // Get all sell
    public static ArrayList<Sell> getAllSell() {
        loadSells();
        return sellsList;

    }

    // Update a sale
    // Update a sale by ID
    public static void updateSale(int sellID, String itemName, double itemPrice, String userName, int sellQuantity, double sellTotalPrice, Date sellDate) {
        Sell sale = getSellById(sellID);
        if (sale != null) {
         sale.setSellID(sellID);
         sale.setItemName(itemName);
         sale.setItemPrice(itemPrice);
         sale.setUserName(userName);
         sale.setSellQuantity(sellQuantity);
         sale.setSellTotalPrice(sellTotalPrice);
         sale.setSellDate(sellDate);
            saveSells();
        } else {
            System.out.println("Sale with ID " + sellID + " not found.");
        }
    }

}