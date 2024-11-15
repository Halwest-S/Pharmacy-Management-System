package DAO;

import model.Recovery;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class RecoveryDAO {
    // SQL queries
    private static final String INSERT_RECOVERY =
            "INSERT INTO Recovery (RecID, ItemID, UserID, RecQuantity, RecTotalPrice, RecDate) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_RECOVERY =
            "UPDATE Recovery SET ItemID = ?, UserID = ?, RecQuantity = ?, RecTotalPrice = ?, RecDate = ? WHERE RecID = ?";
    private static final String DELETE_RECOVERY =
            "DELETE FROM Recovery WHERE RecID = ?";
    private static final String SELECT_ALL_RECOVERIES =
            "SELECT r.RecID, i.CommonName as ItemName, i.ExportPrice as ItemPrice, " +
                    "u.Username, r.RecQuantity, r.RecTotalPrice, r.RecDate " +
                    "FROM Recovery r " +
                    "JOIN Item i ON r.ItemID = i.ItemID " +
                    "JOIN User u ON r.UserID = u.UserID";
    private static final String SELECT_RECOVERY_BY_ID =
            "SELECT r.RecID, i.CommonName as ItemName, i.ExportPrice as ItemPrice, " +
                    "u.Username, r.RecQuantity, r.RecTotalPrice, r.RecDate " +
                    "FROM Recovery r " +
                    "JOIN Item i ON r.ItemID = i.ItemID " +
                    "JOIN User u ON r.UserID = u.UserID " +
                    "WHERE r.RecID = ?";
    private static final String GET_ITEM_ID =
            "SELECT ItemID FROM Item WHERE CommonName = ?";
    private static final String GET_USER_ID =
            "SELECT UserID FROM User WHERE Username = ?";

    private static final String Increase_Item_Quantity = "UPDATE Item SET Quantity = Quantity + ? WHERE ItemID = ?";

    public String addRecovery(Recovery recovery) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Get ItemID from ItemName
                int itemId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_ITEM_ID)) {
                    stmt.setString(1, recovery.getItemName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "Item not found: " + recovery.getItemName();
                    }
                    itemId = rs.getInt("ItemID");
                }

                // Get UserID from Username
                int userId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_USER_ID)) {
                    stmt.setString(1, recovery.getUserName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "User not found: " + recovery.getUserName();
                    }
                    userId = rs.getInt("UserID");
                }

                // Increment the item quantity
                try (PreparedStatement stmt = conn.prepareStatement(Increase_Item_Quantity)) {
                    stmt.setInt(1, recovery.getRecoveryQuantity());
                    stmt.setInt(2, itemId);
                    int quantityUpdated = stmt.executeUpdate();
                    if (quantityUpdated == 0) {
                        conn.rollback();
                        return "Failed to update item quantity for: " + recovery.getItemName();
                    }
                }

                // Insert the recovery
                try (PreparedStatement stmt = conn.prepareStatement(INSERT_RECOVERY)) {
                    stmt.setInt(1, recovery.getRecoveryID());
                    stmt.setInt(2, itemId);
                    stmt.setInt(3, userId);
                    stmt.setInt(4, recovery.getRecoveryQuantity());
                    stmt.setDouble(5, recovery.getRecoveryTotalPrice());
                    stmt.setDate(6, new java.sql.Date(recovery.getRecoveryDate().getTime()));
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        conn.commit();
                        return "Recovery added successfully.";
                    } else {
                        conn.rollback();
                        return "Failed to add recovery.";
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error adding recovery: " + e.getMessage();
        }
    }

    public String removeRecovery(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_RECOVERY)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                return "Recovery removed successfully.";
            } else {
                return "Recovery with ID " + id + " not found.";
            }
        } catch (SQLException e) {
            return "Error removing recovery: " + e.getMessage();
        }
    }

    public String updateRecovery(Recovery recovery) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Get ItemID from ItemName
                int itemId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_ITEM_ID)) {
                    stmt.setString(1, recovery.getItemName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "Item not found: " + recovery.getItemName();
                    }
                    itemId = rs.getInt("ItemID");
                }

                // Get UserID from Username
                int userId;
                try (PreparedStatement stmt = conn.prepareStatement(GET_USER_ID)) {
                    stmt.setString(1, recovery.getUserName());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        return "User not found: " + recovery.getUserName();
                    }
                    userId = rs.getInt("UserID");
                }

                // Fetch the old quantity of the recovery to adjust item stock correctly
                int oldQuantity;
                try (PreparedStatement stmt = conn.prepareStatement(
                        "SELECT RecQuantity FROM Recovery WHERE RecID = ?"
                )) {
                    stmt.setInt(1, recovery.getRecoveryID());
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        oldQuantity = rs.getInt("RecQuantity");
                    } else {
                        return "Recovery with ID " + recovery.getRecoveryID() + " not found.";
                    }
                }

                // Update the item quantity in the Item table
                int quantityDifference = recovery.getRecoveryQuantity() - oldQuantity;
                try (PreparedStatement stmt = conn.prepareStatement(Increase_Item_Quantity)) {
                    stmt.setInt(1, quantityDifference);
                    stmt.setInt(2, itemId);
                    int quantityUpdated = stmt.executeUpdate();
                    if (quantityUpdated == 0) {
                        conn.rollback();
                        return "Failed to update item quantity for: " + recovery.getItemName();
                    }
                }

                // Update the recovery record
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_RECOVERY)) {
                    stmt.setInt(1, itemId);
                    stmt.setInt(2, userId);
                    stmt.setInt(3, recovery.getRecoveryQuantity());
                    stmt.setDouble(4, recovery.getRecoveryTotalPrice());
                    stmt.setDate(5, new java.sql.Date(recovery.getRecoveryDate().getTime()));
                    stmt.setInt(6, recovery.getRecoveryID());

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected == 0) {
                        conn.rollback();
                        return "Recovery with ID " + recovery.getRecoveryID() + " not found.";
                    }
                }

                conn.commit();
                return "Recovery updated successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error updating recovery: " + e.getMessage();
        }
    }

    public Recovery getRecoveryById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_RECOVERY_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Recovery(
                        rs.getInt("RecID"),
                        rs.getString("ItemName"),
                        rs.getDouble("ItemPrice"),
                        rs.getString("Username"),
                        rs.getInt("RecQuantity"),
                        rs.getDouble("RecTotalPrice"),
                        rs.getDate("RecDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Recovery> getAllRecoveries() {
        ArrayList<Recovery> recoveries = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_RECOVERIES);

            while (rs.next()) {
                recoveries.add(new Recovery(
                        rs.getInt("RecID"),
                        rs.getString("ItemName"),
                        rs.getDouble("ItemPrice"),
                        rs.getString("Username"),
                        rs.getInt("RecQuantity"),
                        rs.getDouble("RecTotalPrice"),
                        rs.getDate("RecDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recoveries;
    }
}