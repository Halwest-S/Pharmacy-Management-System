package model;
import java.io.Serializable;
public class Employee implements Serializable   {
    private int employeeID;
    private String employeeName;
    private String employeePassword;

    //constructor
    public Employee(int employeeID, String employeeName,String employeePassword) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeePassword=employeePassword;
    }

    // Getters and Setters
    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
    @Override
    public String toString() {
        return "Employee ( " +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName +
                ", employeePassword='" + employeePassword +
                ')';
    }

}
