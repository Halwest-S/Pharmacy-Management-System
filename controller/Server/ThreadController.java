package controller.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import controller.itemController;
import controller.employeeController; // Ensure this is correctly spelled and imported

import model.Employee;
import model.Item;

public class ThreadController extends Thread {
    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private final itemController itemController;
    private final employeeController employeeController; // Make sure the naming is consistent

    public ThreadController(Socket socket) {
        this.socket = socket;
        this.itemController = new itemController();
        this.employeeController = new employeeController(); // Initialize employeeController
    }

    public void run() {
        try {
            System.out.println("Client Connected");
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String command = (String) inStream.readObject();
                System.out.println("Received command: " + command);

                switch (command) {
                    case "addItem" -> {
                        Item item = (Item) inStream.readObject();
                        System.out.println("Received item to add: " + item);
                        itemController.addItem(item);
                        outStream.writeObject("Item added successfully.");
                        outStream.flush();
                        System.out.println("Item added and response sent to client.");
                    }
                    case "removeItem" -> {
                        System.out.println("Processing 'removeItem' command...");
                        int removeId = (int) inStream.readObject();
                        System.out.println("Received ID to remove: " + removeId);
                        itemController.removeItem(removeId);
                        outStream.writeObject("Item removed successfully (if it existed).");
                        outStream.flush();
                        System.out.println("Item removed and response sent.");
                    }
                    case "getItemById" -> {
                        int id = (int) inStream.readObject();
                        System.out.println("Received ID to retrieve: " + id);
                        Item foundItem = itemController.getItemById(id);
                        outStream.writeObject(foundItem);
                        outStream.flush();
                        System.out.println("Item retrieved and response sent.");
                    }
                    case "getAllItems" -> {
                        System.out.println("Processing getAllItems request");
                        ArrayList<Item> items = itemController.getAllItems();
                        System.out.println("Returning items list to client: " + items);
                        outStream.writeObject(items);
                        outStream.flush();
                    }
                    case "updateItem" -> {
                        Item updatedItem = (Item) inStream.readObject();
                        System.out.println("Received item to update: " + updatedItem);
                        itemController.updateItem(
                                updatedItem.getItemID(),
                                updatedItem.getScientificName(),
                                updatedItem.getCommonName(),
                                updatedItem.getCompany(),
                                updatedItem.getCountry(),
                                updatedItem.getCategory(),
                                updatedItem.getImportPrice(),
                                updatedItem.getExportPrice(),
                                updatedItem.getQuantity(),
                                updatedItem.getImportDate(),
                                updatedItem.getExpiryDate()
                        );
                        outStream.writeObject("Item updated successfully.");
                        outStream.flush();
                        System.out.println("Item updated and response sent.");
                    }
                    case "addEmployee" -> {
                        try {
                            Employee employee = (Employee) inStream.readObject();
                            System.out.println("Received employee to add: " + employee);
                            employeeController.addEmployee(employee);
                            outStream.writeObject("Employee added successfully.");
                        } catch (Exception e) {
                            outStream.writeObject("Error adding employee: " + e.getMessage());
                        }
                        outStream.flush();
                    }
                    case "removeEmployee" -> {
                        System.out.println("Processing 'removeEmployee' command...");
                        int removeEmployeeId = (int) inStream.readObject();
                        System.out.println("Received ID to remove: " + removeEmployeeId);
                        employeeController.removeEmployee(removeEmployeeId);
                        outStream.writeObject("Employee removed successfully (if they existed).");
                        outStream.flush();
                        System.out.println("Employee removed and response sent.");
                    }
                    case "getEmployeeById" -> {
                        int Employeeid = (int) inStream.readObject();
                        System.out.println("Received ID to retrieve: " + Employeeid);
                        Employee foundEmployee = employeeController.getEmployeeById(Employeeid);
                        outStream.writeObject(foundEmployee);
                        outStream.flush();
                        System.out.println("Employee retrieved and response sent.");
                    }
                    case "getAllEmployees" -> {
                        System.out.println("Processing getAllEmployees request");
                        ArrayList<Employee> employees = employeeController.getAllEmployees();
                        System.out.println("Returning employee list to client: " + employees);
                        outStream.writeObject(employees);
                        outStream.flush();
                    }
                    case "updateEmployee" -> {
                        Employee updatedEmployee = (Employee) inStream.readObject();
                        System.out.println("Received employee to update: " + updatedEmployee);
                        employeeController.updateEmployee(
                                updatedEmployee.getEmployeeID(),
                                updatedEmployee.getEmployeeName(),
                                updatedEmployee.getEmployeePassword()
                        );
                        outStream.writeObject("Employee updated successfully.");
                        outStream.flush();
                        System.out.println("Employee updated and response sent.");
                    }
                    default -> {
                        System.out.println("Unknown command received.");
                        outStream.writeObject("Unknown command.");
                        outStream.flush();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
                outStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
