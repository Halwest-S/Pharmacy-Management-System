package controller;

import model.Employee;

import java.util.ArrayList;

public class employeeController {
    private final ArrayList<Employee> employeeList = new ArrayList<>();

    // Add employee if not already in the list
    public void addEmployee(Employee employee) {
        if (getEmployeeById(employee.getEmployeeID()) == null) {
            employeeList.add(employee);
        } else {
            System.out.println("Employee with ID " + employee.getEmployeeID() + " already exists.");
        }
    }

    // Remove employee by ID
    public void removeEmployee(int id) {
        employeeList.removeIf((Employee employee) -> employee.getEmployeeID() == id);
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getEmployeeID() == id) {
                return employee;
            }
        }
        return null;
    }

    // Get all employees
    public ArrayList<Employee> getAllEmployees() {
        return employeeList;
    }

    // Update employee details by ID
    public void updateEmployee(int id, String employeeName, String employeePassword) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setEmployeeName(employeeName);
            employee.setEmployeePassword(employeePassword);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
}
