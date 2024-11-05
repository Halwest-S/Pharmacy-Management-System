package DAO;


import model.Employee;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;



public class EmployeeDAO {
    // SQL queries
    private static final String INSERT_USER = "INSERT INTO User (UserID, Username, Password, Role) VALUES (?, ?, ?, ?)";
    private static final String INSERT_EMPLOYEE = "INSERT INTO Employee (EmpID, UserID) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE User SET Username = ?, Password = ? WHERE UserID = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE EmpID = ?";
    private static final String DELETE_USER = "DELETE FROM User WHERE UserID = ?";
    private static final String SELECT_ALL_EMPLOYEES =
            "SELECT e.EmpID, u.UserID, u.Username, u.Password " +
                    "FROM Employee e " +
                    "JOIN User u ON e.UserID = u.UserID";
    private static final String SELECT_EMPLOYEE_BY_ID =
            "SELECT e.EmpID, u.UserID, u.Username, u.Password " +
                    "FROM Employee e " +
                    "JOIN User u ON e.UserID = u.UserID " +
                    "WHERE e.EmpID = ?";


    public String addEmployee(Employee employee) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                if (getEmployeeById(employee.getEmployeeID()) != null) {
                    return "Employee with ID " + employee.getEmployeeID() + " already exists.";
                }


                try (PreparedStatement userStmt = conn.prepareStatement(INSERT_USER)) {
                    userStmt.setInt(1, employee.getEmployeeID());
                    userStmt.setString(2, employee.getEmployeeName());
                    userStmt.setString(3, employee.getEmployeePassword());
                    userStmt.setString(4, "EMPLOYEE");
                    userStmt.executeUpdate();
                }


                try (PreparedStatement empStmt = conn.prepareStatement(INSERT_EMPLOYEE)) {
                    empStmt.setInt(1, employee.getEmployeeID());
                    empStmt.setInt(2, employee.getEmployeeID());
                    empStmt.executeUpdate();
                }

                conn.commit();
                return "Employee added successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error adding employee: " + e.getMessage();
        }
    }


    public String removeEmployee(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (getEmployeeById(id) == null) {
                return "Employee with ID " + id + " not found.";
            }

            conn.setAutoCommit(false);
            try {

                try (PreparedStatement empStmt = conn.prepareStatement(DELETE_EMPLOYEE)) {
                    empStmt.setInt(1, id);
                    empStmt.executeUpdate();
                }


                try (PreparedStatement userStmt = conn.prepareStatement(DELETE_USER)) {
                    userStmt.setInt(1, id);
                    userStmt.executeUpdate();
                }

                conn.commit();
                return "Employee removed successfully.";
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            return "Error removing employee: " + e.getMessage();
        }
    }


    public String updateEmployee(Employee employee) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {

            if (getEmployeeById(employee.getEmployeeID()) == null) {
                return "Employee with ID " + employee.getEmployeeID() + " not found.";
            }

            stmt.setString(1, employee.getEmployeeName());
            stmt.setString(2, employee.getEmployeePassword());
            stmt.setInt(3, employee.getEmployeeID());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Employee updated successfully.";
            } else {
                return "Failed to update employee.";
            }
        } catch (SQLException e) {
            return "Error updating employee: " + e.getMessage();
        }
    }


    public Employee getEmployeeById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("EmpID"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_EMPLOYEES);

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("EmpID"),
                        rs.getString("Username"),
                        rs.getString("Password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}