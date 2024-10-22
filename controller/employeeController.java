package controller;

import model.Employee;
import Util.FileUtil;

import java.util.ArrayList;

public class employeeController {
    private ArrayList<Employee> employeeList;
    private static final String FILE_NAME = "employees.txt";

    public employeeController() {
        loadEmployees();
    }

    private void loadEmployees() {
        employeeList = (ArrayList<Employee>) FileUtil.readFromFile(FILE_NAME);
        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }
    }

    private void saveEmployees() {
        FileUtil.writeToFile(FILE_NAME, employeeList);
    }


    public void addEmployee(Employee employee) {
        if (getEmployeeById(employee.getEmployeeID()) == null) {
            employeeList.add(employee);
            saveEmployees();
        } else {
            System.out.println("Employee with ID " + employee.getEmployeeID() + " already exists.");
        }
    }

    public void removeEmployee(int id) {
        employeeList.removeIf((Employee employee) -> employee.getEmployeeID() == id);
        saveEmployees();
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getEmployeeID() == id) {
                return employee;  // Simply return the found employee
            }
        }
        return null;  // Return null if not found
    }


    public ArrayList<Employee> getAllEmployees() {
        loadEmployees();
        return employeeList;
    }

    public void updateEmployee(int id, String employeeName, String employeePassword) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setEmployeeName(employeeName);
            employee.setEmployeePassword(employeePassword);
            saveEmployees();
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
}