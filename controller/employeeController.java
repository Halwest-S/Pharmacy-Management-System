package controller;

import DAO.EmployeeDAO;
import model.Employee;

import java.util.ArrayList;

public class employeeController {
    private EmployeeDAO employeeDAO;

    public employeeController() {
        employeeDAO = new EmployeeDAO();  // Initialize EmployeeDAO for database operations
    }

    // Add a new employee using database
    public String addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    // Remove an employee from database
    public String removeEmployee(int id) {
        return employeeDAO.removeEmployee(id);
    }

    // Get an employee by ID from database
    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    // Retrieve all employees from database
    public ArrayList<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    // Update an employee's details in the database
    public String updateEmployee(int id, String employeeName, String employeePassword) {
        Employee employee = new Employee(id, employeeName, employeePassword);
        return employeeDAO.updateEmployee(employee);
    }
}
