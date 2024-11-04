package controller;
import model.Employee;
import java.util.ArrayList;
import DAO.EmployeeDAO;

public class employeeController {
    private EmployeeDAO employeeDAO;

    public employeeController() {
        employeeDAO = new EmployeeDAO();
    }

    public void addEmployee(Employee employee) {
        if (employeeDAO.getEmployeeById(employee.getEmployeeID()) == null) {
            employeeDAO.addEmployee(employee);
        } else {
            System.out.println("Employee with ID " + employee.getEmployeeID() + " already exists.");
        }
    }

    public void removeEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public ArrayList<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployee(int id, String employeeName, String employeePassword) {
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee != null) {
            employee.setEmployeeName(employeeName);
            employee.setEmployeePassword(employeePassword);
            employeeDAO.updateEmployee(employee);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
}
