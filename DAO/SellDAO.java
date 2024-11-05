package DAO;


import Util.DatabaseConnection;
import model.Sell;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class SellDAO {
    // SQL queries
    private static final String INSERT_SELL = "INSERT INTO Sell (SellID, ItemID, UserID, SellQuantity, SellTotalPrice, SellDate) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SELL = "UPDATE Sell SET ItemID = ?, UserID = ?, SellQuantity = ?, SellTotalPrice = ?, SellDate = ? WHERE SellID = ?";
    private static final String DELETE_SELL = "DELETE FROM Sell WHERE SellID = ?";
    private static final String SELECT_ALL_SELLS =
            "SELECT s.SellID, i.CommonName as ItemName, i.ExportPrice as ItemPrice, " +
                    "u.Username, s.SellQuantity, s.SellTotalPrice, s.SellDate " +
                    "FROM Sell s " +
                    "JOIN Item i ON s.ItemID = i.ItemID " +
                    "JOIN User u ON s.UserID = u.UserID";
    private static final String SELECT_SELL_BY_ID =
            "SELECT s.SellID, i.CommonName as ItemName, i.ExportPrice as ItemPrice, " +
                    "u.Username, s.SellQuantity, s.SellTotalPrice, s.SellDate " +
                    "FROM Sell s " +
                    "JOIN Item i ON s.ItemID = i.ItemID " +
                    "JOIN User u ON s.UserID = u.UserID " +
                    "WHERE s.SellID = ?";
    private static final String GET_ITEM_ID = "SELECT ItemID FROM Item WHERE CommonName = ?";
    private static final String GET_USER_ID = "SELECT UserID FROM User WHERE Username = ?";

    // In SellDAO class
    public String addSell(Sell sell) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Debug print
                System.out.println("Attempting to add sale: " + sell.getSellID());

                // Get ItemID from ItemName
                int itemId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_ITEM_ID)) {
                    stmt.setString(1, sell.getItemName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        System.out.println("Item not found: " + sell.getItemName());
                        return "Item not found: " + sell.getItemName();
                    }
                    itemId = rs.getInt("ItemID");
                    System.out.println("Found ItemID: " + itemId);
                }

                // Get UserID from Username
                int userId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_USER_ID)) {
                    stmt.setString(1, sell.getUserName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        System.out.println("User not found: " + sell.getUserName());
                        return "User not found: " + sell.getUserName();
                    }
                    userId = rs.getInt("UserID");
                    System.out.println("Found UserID: " + userId);
                }

                // Insert the sale
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_SELL)) {
                    stmt.setInt(1, sell.getSellID());
                    stmt.setInt(2, itemId);
                    stmt.setInt(3, userId);
                    stmt.setInt(4, sell.getSellQuantity());
                    stmt.setDouble(5, sell.getSellTotalPrice());
                    stmt.setDate(6, new java.sql.Date(sell.getSellDate().getTime()));

                    int rowsAffected = stmt.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);

                    if (rowsAffected > 0) {
                        conn.commit();
                        return "Sale added successfully.";
                    } else {
                        conn.rollback();
                        return "Failed to add sale.";
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return "Error adding sale: " + e.getMessage();
        }
    }

    public String removeSell(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SELL)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return "Sale removed successfully.";
            } else {
                return "Sale with ID " + id + " not found.";
            }
        } catch (SQLException e) {
            return "Error removing sale: " + e.getMessage();
        }
    }

    public String updateSell(Sell sell) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Get ItemID from ItemName
                int itemId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_ITEM_ID)) {
                    stmt.setString(1, sell.getItemName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "Item not found: " + sell.getItemName();
                    }
                    itemId = rs.getInt("ItemID");
                }

                // Get UserID from Username
                int userId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_USER_ID)) {
                    stmt.setString(1, sell.getUserName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "User not found: " + sell.getUserName();
                    }
                    userId = rs.getInt("UserID");
                }

                // Update the sale
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_SELL)) {
                    stmt.setInt(1, itemId);
                    stmt.setInt(2, userId);
                    stmt.setInt(3, sell.getSellQuantity());
                    stmt.setDouble(4, sell.getSellTotalPrice());
                    stmt.setDate(5, new java.sql.Date(sell.getSellDate().getTime()));
                    stmt.setInt(6, sell.getSellID());

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected == 0) {
                        return "Sale with ID " + sell.getSellID() + " not found.";
                    }
                }

                conn.commit();
                return "Sale updated successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error updating sale: " + e.getMessage();
        }
    }

    public Sell getSellById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SELL_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sell(
                        rs.getInt("SellID"),
                        rs.getString("ItemName"),
                        rs.getDouble("ItemPrice"),
                        rs.getString("Username"),
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

    public ArrayList<Sell> getAllSells() {
        ArrayList<Sell> sells = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_SELLS);

            while (rs.next()) {
                sells.add(new Sell(
                        rs.getInt("SellID"),
                        rs.getString("ItemName"),
                        rs.getDouble("ItemPrice"),
                        rs.getString("Username"),
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
}