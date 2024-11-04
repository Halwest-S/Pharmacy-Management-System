package DAO;

import model.Item;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAO {
    // SQL queries
    private static final String INSERT_ITEM = "INSERT INTO Item (itemID, scientificName, commonName, company, country, category, importPrice, exportPrice, quantity, importDate, expiryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM Item WHERE itemID = ?";
    private static final String SELECT_ALL_ITEMS = "SELECT * FROM Item";
    private static final String SELECT_ITEM_BY_ID = "SELECT * FROM Item WHERE itemID = ?";
    private static final String UPDATE_ITEM = "UPDATE Item SET scientificName = ?, commonName = ?, company = ?, country = ?, category = ?, importPrice = ?, exportPrice = ?, quantity = ?, importDate = ?, expiryDate = ? WHERE itemID = ?";

    // Add an item - returns String response for client
    public String addItem(Item item) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ITEM)) {
                preparedStatement.setInt(1, item.getItemID());
                preparedStatement.setString(2, item.getScientificName());
                preparedStatement.setString(3, item.getCommonName());
                preparedStatement.setString(4, item.getCompany());
                preparedStatement.setString(5, item.getCountry());
                preparedStatement.setString(6, item.getCategory());
                preparedStatement.setDouble(7, item.getImportPrice());
                preparedStatement.setDouble(8, item.getExportPrice());
                preparedStatement.setInt(9, item.getQuantity());
                preparedStatement.setString(10, item.getImportDate());
                preparedStatement.setString(11, item.getExpiryDate());
                preparedStatement.executeUpdate();
                conn.commit();
                return "Item added successfully.";
            } catch (SQLException e) {
                conn.rollback();
                return "Error adding item: " + e.getMessage();
            }
        } catch (SQLException e) {
            return "Error connecting to database: " + e.getMessage();
        }
    }

    // Remove an item - returns String response for client
    public String removeItem(int itemID) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (getItemById(itemID) == null) {
                return "Item with ID " + itemID + " not found.";
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_ITEM)) {
                preparedStatement.setInt(1, itemID);
                preparedStatement.executeUpdate();
                return "Item removed successfully.";
            }
        } catch (SQLException e) {
            return "Error removing item: " + e.getMessage();
        }
    }

    // Update an item - returns String response for client
    public String updateItem(Item item) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (getItemById(item.getItemID()) == null) {
                return "Item with ID " + item.getItemID() + " not found.";
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ITEM)) {
                preparedStatement.setString(1, item.getScientificName());
                preparedStatement.setString(2, item.getCommonName());
                preparedStatement.setString(3, item.getCompany());
                preparedStatement.setString(4, item.getCountry());
                preparedStatement.setString(5, item.getCategory());
                preparedStatement.setDouble(6, item.getImportPrice());
                preparedStatement.setDouble(7, item.getExportPrice());
                preparedStatement.setInt(8, item.getQuantity());
                preparedStatement.setString(9, item.getImportDate());
                preparedStatement.setString(10, item.getExpiryDate());
                preparedStatement.setInt(11, item.getItemID());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return "Item updated successfully.";
                } else {
                    return "Failed to update item.";
                }
            }
        } catch (SQLException e) {
            return "Error updating item: " + e.getMessage();
        }
    }

    // Get item by ID - helper method
    public Item getItemById(int itemID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ITEM_BY_ID)) {

            preparedStatement.setInt(1, itemID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Item(
                        resultSet.getInt("itemID"),
                        resultSet.getString("scientificName"),
                        resultSet.getString("commonName"),
                        resultSet.getString("company"),
                        resultSet.getString("country"),
                        resultSet.getString("category"),
                        resultSet.getDouble("importPrice"),
                        resultSet.getDouble("exportPrice"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("importDate"),
                        resultSet.getString("expiryDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all items
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ITEMS);
            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getInt("itemID"),
                        resultSet.getString("scientificName"),
                        resultSet.getString("commonName"),
                        resultSet.getString("company"),
                        resultSet.getString("country"),
                        resultSet.getString("category"),
                        resultSet.getDouble("importPrice"),
                        resultSet.getDouble("exportPrice"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("importDate"),
                        resultSet.getString("expiryDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total items retrieved: " + items.size()); // Debugging line
        return items;
    }

}
