package controller;

import DAO.ItemDAO;
import Util.FileUtil;
import model.Item;

import java.util.ArrayList;


import java.sql.SQLException;


public class itemController {
    private static ItemDAO itemDAO;

    public itemController() {
        itemDAO = new ItemDAO();
    }

    public void addItem(Item item) {
        itemDAO.addItem(item);
        System.out.println("Item added successfully.");
    }

    public void removeItem(int id) {
        itemDAO.removeItem(id);
        System.out.println("Item removed successfully.");
    }

    public Item getItemById(int id) {
        return itemDAO.getItemById(id);
    }

    public static ArrayList<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    public String updateItem(int itemID, String scientificName, String commonName, String company,
                             String country, String category, double importPrice,
                             double exportPrice, int quantity, String importDate,
                             String expiryDate) {
        Item item = new Item(itemID, scientificName, commonName, company, country, category,
                importPrice, exportPrice, quantity, importDate, expiryDate);
        return itemDAO.updateItem(item);
    }


}
