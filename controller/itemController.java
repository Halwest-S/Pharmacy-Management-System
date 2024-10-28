package controller;

import Util.FileUtil;
import model.Item;

import java.util.ArrayList;

public class itemController {
    private static ArrayList<Item> itemsList;
    private static final String FILE_NAME = "item.txt";

    // Constructor that loads items from file when the controller is created
    public itemController() {
        loadItems();
    }

    // Load items from file or initialize if file is empty
    private static void loadItems() {
        itemsList = (ArrayList<Item>) FileUtil.readFromFile(FILE_NAME);
        if (itemsList == null) {
            itemsList = new ArrayList<>();
        }
    }

    // Save items to file
    private void saveItems() {
        FileUtil.writeToFile(FILE_NAME, itemsList);
    }

    // Add item if not already in the list
    public void addItem(Item item) {
        if (getItemById(item.getItemID()) == null) {
            itemsList.add(item);
            saveItems();
        } else {
            System.out.println("Item with ID " + item.getItemID() + " already exists.");
        }
    }

    // Remove item by ID
    public void removeItem(int id) {
        itemsList.removeIf((Item item) -> item.getItemID() == id);
        saveItems();
    }

    // Get item by ID
    public Item getItemById(int id) {
        for (Item item : itemsList) {
            if (item.getItemID() == id) {
                return item;
            }
        }
        return null;
    }

    // Get all items (itemsList is already initialized in constructor)
    public static ArrayList<Item> getAllItems() {
        return itemsList;
    }

    // Update item details by ID
    public void updateItem(int id, String scientificName, String commonName, String company, String country,
                           String category, double importPrice, double exportPrice, int quantity, String importDate, String expiryDate) {
        Item item = getItemById(id);
        if (item != null) {
            item.setScientificName(scientificName);
            item.setCommonName(commonName);
            item.setCompany(company);
            item.setCountry(country);
            item.setCategory(category);
            item.setImportPrice(importPrice);
            item.setExportPrice(exportPrice);
            item.setQuantity(quantity);
            item.setImportDate(importDate);
            item.setExpiryDate(expiryDate);
            saveItems();
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }
}
