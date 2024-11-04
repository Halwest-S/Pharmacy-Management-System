package DAO;


import Util.DatabaseConnection;
import model.Sell;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class SellDAO {
    // SQL queries
    private static final String INSERT_SELL = "INSERT INTO Sell (SellID, ItemID, UserID, SellQuantity, SellTotalPrice, SellDate) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SELL = "DELETE FROM Sell WHERE SellID = ?";
    private static final String SELECT_SELL_BY_ID = "SELECT s.SellID, i.CommonName AS itemName, i.ExportPrice AS itemPrice, u.Username AS userName, s.SellQuantity, s.SellTotalPrice, s.SellDate " +
            "FROM Sell s JOIN Item i ON s.ItemID = i.ItemID JOIN User u ON s.UserID = u.UserID WHERE s.SellID = ?";
    private static final String SELECT_ALL_SELLS = "SELECT s.SellID, i.CommonName AS itemName, i.ExportPrice AS itemPrice, u.Username AS userName, s.SellQuantity, s.SellTotalPrice, s.SellDate " +
            "FROM Sell s JOIN Item i ON s.ItemID = i.ItemID JOIN User u ON s.UserID = u.UserID";
    private static final String UPDATE_SELL = "UPDATE Sell SET ItemID = ?, UserID = ?, SellQuantity = ?, SellTotalPrice = ?, SellDate = ? WHERE SellID = ?";
    private static final String SELECT_ITEM_ID_BY_NAME = "SELECT ItemID FROM Item WHERE CommonName = ?";
    private static final String SELECT_USER_ID_BY_NAME = "SELECT UserID FROM User WHERE Username = ?";

    // Add a new sale - returns String response for client
    public String addSell(Sell sell) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Check if the sell already exists
                if (getSellById(sell.getSellID()) != null) {
                    return "Sale with ID " + sell.getSellID() + " already exists.";
                }

                // Insert the new sale into the database
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_SELL)) {
                    stmt.setInt(1, sell.getSellID());
                    stmt.setInt(2, getItemIdByName(sell.getItemName())); // Fetch ItemID based on itemName
                    stmt.setInt(3, getUserIdByName(sell.getUserName())); // Fetch UserID based on userName
                    stmt.setInt(4, sell.getSellQuantity());
                    stmt.setDouble(5, sell.getSellTotalPrice());
                    stmt.setDate(6, new java.sql.Date(sell.getSellDate().getTime()));
                    stmt.executeUpdate();
                }

                conn.commit();
                return "Sale added successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error adding sale: " + e.getMessage();
        }
    }

    // Remove a sale - returns String response for client
    public String removeSell(int sellID) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (getSellById(sellID) == null) {
                return "Sale with ID " + sellID + " not found.";
            }

            conn.setAutoCommit(false);
            try {
                try (PreparedStatement stmt = conn.prepareStatement(DELETE_SELL)) {
                    stmt.setInt(1, sellID);
                    stmt.executeUpdate();
                }

                conn.commit();
                return "Sale removed successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error removing sale: " + e.getMessage();
        }
    }

    // Update a sale - returns String response for client
    public String updateSell(Sell sell) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (getSellById(sell.getSellID()) == null) {
                return "Sale with ID " + sell.getSellID() + " not found.";
            }

            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SELL)) {
                stmt.setInt(1, getItemIdByName(sell.getItemName())); // Fetch ItemID based on itemName
                stmt.setInt(2, getUserIdByName(sell.getUserName())); // Fetch UserID based on userName
                stmt.setInt(3, sell.getSellQuantity());
                stmt.setDouble(4, sell.getSellTotalPrice());
                stmt.setDate(5, new java.sql.Date(sell.getSellDate().getTime()));
                stmt.setInt(6, sell.getSellID());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Sale updated successfully.";
                } else {
                    return "Failed to update sale.";
                }
            }
        } catch (SQLException e) {
            return "Error updating sale: " + e.getMessage();
        }
    }

    // Get a sale by ID - helper method
    public Sell getSellById(int sellID) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SELL_BY_ID)) {

            stmt.setInt(1, sellID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sell(
                        rs.getInt("SellID"),
                        rs.getString("itemName"),
                        rs.getDouble("itemPrice"),
                        rs.getString("userName"),
                        rs.getInt("SellQuantity"),
                        rs.getDouble("SellTotalPrice"),
                        rs.getDate("SellDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all sales
    public ArrayList<Sell> getAllSells() {
        ArrayList<Sell> sells = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_SELLS);

            while (rs.next()) {
                sells.add(new Sell(
                        rs.getInt("SellID"),
                        rs.getString("itemName"),
                        rs.getDouble("itemPrice"),
                        rs.getString("userName"),
                        rs.getInt("SellQuantity"),
                        rs.getDouble("SellTotalPrice"),
                        rs.getDate("SellDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sells;
    }

    // Helper method to get ItemID by item name
    private int getItemIdByName(String itemName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ITEM_ID_BY_NAME)) {

            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ItemID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if not found
    }

    // Helper method to get UserID by user name
    private int getUserIdByName(String userName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_ID_BY_NAME)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if not found
    }
}
