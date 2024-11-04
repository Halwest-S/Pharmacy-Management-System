package DAO;


import model.Employee;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;



    public class EmployeeDAO {

        public void addEmployee(Employee employee) {
            String insertUserSQL = "INSERT INTO User (Username, Password, RoleID) VALUES (?, ?, ?)";
            String insertEmployeeSQL = "INSERT INTO Employee (UserID) VALUES (?)";

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Insert into User table first
                PreparedStatement userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
                userStmt.setString(1, employee.getEmployeeName()); // Assuming employeeName is used as the username
                userStmt.setString(2, employee.getEmployeePassword());
                userStmt.setInt(3, 2); // Assuming '2' is the RoleID for Employee
                userStmt.executeUpdate();

                // Get the generated UserID
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);

                    // Insert into Employee table
                    PreparedStatement employeeStmt = conn.prepareStatement(insertEmployeeSQL);
                    employeeStmt.setInt(1, userID);
                    employeeStmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteEmployee(int employeeID) {
            String deleteEmployeeSQL = "DELETE FROM Employee WHERE EmployeeID = ?";
            String deleteUserSQL = "DELETE FROM User WHERE UserID = (SELECT UserID FROM Employee WHERE EmployeeID = ?)";

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Delete from Employee table first
                PreparedStatement employeeStmt = conn.prepareStatement(deleteEmployeeSQL);
                employeeStmt.setInt(1, employeeID);
                employeeStmt.executeUpdate();

                // Then delete the corresponding user
                PreparedStatement userStmt = conn.prepareStatement(deleteUserSQL);
                userStmt.setInt(1, employeeID);
                userStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Employee getEmployeeById(int employeeID) {
            String sql = "SELECT u.Username, u.Password FROM Employee e INNER JOIN User u ON e.UserID = u.UserID WHERE e.EmployeeID = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, employeeID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Employee(employeeID, rs.getString("Username"), rs.getString("Password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Employee> getAllEmployees() {
            String sql = "SELECT e.EmployeeID, u.Username, u.Password FROM Employee e INNER JOIN User u ON e.UserID = u.UserID";
            ArrayList<Employee> employees = new ArrayList<>();

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee(rs.getInt("EmployeeID"), rs.getString("Username"), rs.getString("Password"));
                    employees.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employees;
        }

        public void updateEmployee(Employee employee) {
            String updateUserSQL = "UPDATE User SET Username = ?, Password = ? WHERE UserID = (SELECT UserID FROM Employee WHERE EmployeeID = ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(updateUserSQL)) {
                stmt.setString(1, employee.getEmployeeName());
                stmt.setString(2, employee.getEmployeePassword());
                stmt.setInt(3, employee.getEmployeeID());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
