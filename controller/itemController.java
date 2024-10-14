package controller;

        import model.Item;

        import java.util.ArrayList;

public class itemController {
    private final ArrayList<Item> itemsList = new ArrayList<>();

    // Add item if not already in the list
    public void addItem(Item item) {
        if (getItemById(item.getItemID()) == null) {
            itemsList.add(item);
        } else {
            System.out.println("Item with ID " + item.getItemID() + " already exists.");
        }
    }

    // Remove item by ID
    public void removeItem(int id) {
        itemsList.removeIf((Item item) -> item.getItemID() == id);
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

    // Get all items
    public ArrayList<Item> getAllItems() {
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
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }
}
