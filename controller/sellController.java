package controller;

import model.Sell;

import java.util.ArrayList;

public class sellController {
    private final ArrayList<Sell> sellsList = new ArrayList<>();

    // Add a new sell
    public void addSell(Sell sell) {
        sellsList.add(sell);
    }

    // Remove a sale by ID
    public void removeSell(int id) {
        sellsList.removeIf(sell -> sell.getSellID() == id);
    }

    // Get a sale by ID
    public Sell getSellById(int id) {
        for (Sell sell : sellsList) {
            if (sell.getSellID() == id) {
                return sell;
            }
        }
        return null; // Sale not found
    }

    // Get all sell
    public ArrayList<Sell> getAllSell() {
        return new ArrayList<>(sellsList);
    }

    // Update a sale
    public void updateSale(int id, Sell updatedSale) {
        for (int i = 0; i < sellsList.size(); i++) {
            if (sellsList.get(i).getSellID() == id) {
                sellsList.set(i, updatedSale);
                return; // Sale updated successfully
            }
        }
    }
}
