package controller.Server;

import controller.employeeController;
import controller.itemController;
import controller.recoveryController;
import controller.sellController;
import model.Employee;
import model.Item;
import model.Recovery;
import model.Sell;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private final Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final itemController itemController;
    private final employeeController employeeController;
    private final sellController sellController;
    private final recoveryController recoveryController;
    private boolean running;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.itemController = new itemController();
        this.employeeController = new employeeController();
        this.sellController = new sellController();
        this.recoveryController = new recoveryController();
        this.running = true;
    }

    @Override
    public void run() {
        try {
            setupStreams();
            handleClientRequests();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error in client communication", e);
        } finally {
            cleanup();
        }
    }

    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        input = new ObjectInputStream(clientSocket.getInputStream());
    }

    private void handleClientRequests() {
        while (running) {
            try {
                String command = (String) input.readObject();
                LOGGER.info("Received command: " + command);

                handleCommand(command);

            } catch (EOFException e) {
                LOGGER.info("Client disconnected normally");
                break;
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Error processing client request", e);
                break;
            }
        }
    }

    private void handleCommand(String command) throws IOException, ClassNotFoundException {
        try {
            switch (command) {

                case "addItem" -> handleAddItem();
                case "removeItem" -> handleRemoveItem();
                case "getItemById" -> handleGetItemById();
                case "getAllItems" -> handleGetAllItems();
                case "updateItem" -> handleUpdateItem();

                // Employee operations
                case "addEmployee" -> handleAddEmployee();
                case "removeEmployee" -> handleRemoveEmployee();
                case "getEmployeeById" -> handleGetEmployeeById();
                case "getAllEmployees" -> handleGetAllEmployees();
                case "updateEmployee" -> handleUpdateEmployee();

                // Sale operations
                case "addSell" -> handleAddSell();
                case "removeSell" -> handleRemoveSell();
                case "getSellById" -> handleGetSellById();
                case "getAllSells" -> handleGetAllSells();
                case "updateSell" -> handleUpdateSell();

                // Recovery operations
                case "addRecovery" -> handleAddRecovery();
                case "removeRecovery" -> handleRemoveRecovery();
                case "getRecoveryById" -> handleGetRecoveryById();
                case "getAllRecoveries" -> handleGetAllRecoveries();
                case "updateRecovery" -> handleUpdateRecovery();

                default -> sendResponse("Unknown command");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error handling command: " + command, e);
            sendResponse("Error processing request: " + e.getMessage());
        }
    }

    // Item Handlers
    private void handleAddItem() throws IOException, ClassNotFoundException {
        Item item = (Item) input.readObject();
        try {
            itemController.addItem(item);
            sendResponse("Item added successfully");
        } catch (Exception e) {
            sendResponse("Error adding item: " + e.getMessage());
        }
    }

    private void handleRemoveItem() throws IOException, ClassNotFoundException {
        Integer itemId = (Integer) input.readObject();
        try {
            itemController.removeItem(itemId);
            sendResponse("Item removed successfully");
        } catch (Exception e) {
            sendResponse("Error removing item: " + e.getMessage());
        }
    }

    private void handleGetItemById() throws IOException, ClassNotFoundException {
        Integer itemId = (Integer) input.readObject();
        try {
            Item item = itemController.getItemById(itemId);
            output.writeObject(item);
        } catch (Exception e) {
            sendResponse("Error retrieving item: " + e.getMessage());
        }
    }

    private void handleGetAllItems() throws IOException {
        try {
            ArrayList<Item> items = itemController.getAllItems();
            output.writeObject(items);
        } catch (Exception e) {
            sendResponse("Error retrieving items: " + e.getMessage());
        }
    }

    private void handleUpdateItem() throws IOException, ClassNotFoundException {
        Item item = (Item) input.readObject();
        try {
            itemController.updateItem(
                    item.getItemID(),
                    item.getScientificName(),
                    item.getCommonName(),
                    item.getCompany(),
                    item.getCountry(),
                    item.getCategory(),
                    item.getImportPrice(),
                    item.getExportPrice(),
                    item.getQuantity(),
                    item.getImportDate(),
                    item.getExpiryDate()
            );
            sendResponse("Item updated successfully");
        } catch (Exception e) {
            sendResponse("Error updating item: " + e.getMessage());
        }
    }

    // Employee Handlers
    private void handleAddEmployee() throws IOException, ClassNotFoundException {
        Employee employee = (Employee) input.readObject();
        try {
            employeeController.addEmployee(employee);
            sendResponse("Employee added successfully");
        } catch (Exception e) {
            sendResponse("Error adding employee: " + e.getMessage());
        }
    }

    private void handleRemoveEmployee() throws IOException, ClassNotFoundException {
        Integer employeeId = (Integer) input.readObject();
        try {
            employeeController.removeEmployee(employeeId);
            sendResponse("Employee removed successfully");
        } catch (Exception e) {
            sendResponse("Error removing employee: " + e.getMessage());
        }
    }

    private void handleGetEmployeeById() throws IOException, ClassNotFoundException {
        Integer employeeId = (Integer) input.readObject();
        try {
            Employee employee = employeeController.getEmployeeById(employeeId);
            output.writeObject(employee);
        } catch (Exception e) {
            sendResponse("Error retrieving employee: " + e.getMessage());
        }
    }

    private void handleGetAllEmployees() throws IOException {
        try {
            ArrayList<Employee> employees = employeeController.getAllEmployees();
            output.writeObject(employees);
        } catch (Exception e) {
            sendResponse("Error retrieving employees: " + e.getMessage());
        }
    }

    private void handleUpdateEmployee() throws IOException, ClassNotFoundException {
        Employee employee = (Employee) input.readObject();
        try {
            employeeController.updateEmployee(
                    employee.getEmployeeID(),
                    employee.getEmployeeName(),
                    employee.getEmployeePassword()
            );
            sendResponse("Employee updated successfully");
        } catch (Exception e) {
            sendResponse("Error updating employee: " + e.getMessage());
        }
    }

    // Sell Handlers
    private void handleAddSell() throws IOException, ClassNotFoundException {
        Sell sell = (Sell) input.readObject();
        try {
            sellController.addSell(sell);
            sendResponse("Sale added successfully");
        } catch (Exception e) {
            sendResponse("Error adding sale: " + e.getMessage());
        }
    }

    private void handleRemoveSell() throws IOException, ClassNotFoundException {
        Integer sellId = (Integer) input.readObject();
        try {
            sellController.removeSell(sellId);
            sendResponse("Sale removed successfully");
        } catch (Exception e) {
            sendResponse("Error removing sale: " + e.getMessage());
        }
    }

    private void handleGetSellById() throws IOException, ClassNotFoundException {
        Integer sellId = (Integer) input.readObject();
        try {
            Sell sell = sellController.getSellById(sellId);
            output.writeObject(sell);
        } catch (Exception e) {
            sendResponse("Error retrieving sale: " + e.getMessage());
        }
    }

    private void handleGetAllSells() throws IOException {
        try {
            ArrayList<Sell> sells = sellController.getAllSell();
            output.writeObject(sells);
        } catch (Exception e) {
            sendResponse("Error retrieving sales: " + e.getMessage());
        }
    }

    private void handleUpdateSell() throws IOException, ClassNotFoundException {
        Sell sell = (Sell) input.readObject();
        try {
            sellController.updateSale(
                    sell.getSellID(),
                    sell.getItemName(),
                    sell.getItemPrice(),
                    sell.getUserName(),
                    sell.getSellQuantity(),
                    sell.getSellTotalPrice(),
                    sell.getSellDate()
            );
            sendResponse("Sale updated successfully");
        } catch (Exception e) {
            sendResponse("Error updating sale: " + e.getMessage());
        }
    }

    // Recovery Handlers
    private void handleAddRecovery() throws IOException, ClassNotFoundException {
        Recovery recovery = (Recovery) input.readObject();
        try {
            recoveryController.addRecovery(recovery);
            sendResponse("Recovery added successfully");
        } catch (Exception e) {
            sendResponse("Error adding recovery: " + e.getMessage());
        }
    }

    private void handleRemoveRecovery() throws IOException, ClassNotFoundException {
        Integer recoveryId = (Integer) input.readObject();
        try {
            recoveryController.removeRecovery(recoveryId);
            sendResponse("Recovery removed successfully");
        } catch (Exception e) {
            sendResponse("Error removing recovery: " + e.getMessage());
        }
    }

    private void handleGetRecoveryById() throws IOException, ClassNotFoundException {
        Integer recoveryId = (Integer) input.readObject();
        try {
            Recovery recovery = recoveryController.getRecoveryById(recoveryId);
            output.writeObject(recovery);
        } catch (Exception e) {
            sendResponse("Error retrieving recovery: " + e.getMessage());
        }
    }

    private void handleGetAllRecoveries() throws IOException {
        try {
            ArrayList<Recovery> recoveries = recoveryController.getAllRecoveries();
            output.writeObject(recoveries);
        } catch (Exception e) {
            sendResponse("Error retrieving recoveries: " + e.getMessage());
        }
    }

    private void handleUpdateRecovery() throws IOException, ClassNotFoundException {
        Recovery recovery = (Recovery) input.readObject();
        try {
            recoveryController.updateRecovery(
                    recovery.getRecoveryID(),
                    recovery.getItemName(),
                    recovery.getItemPrice(),
                    recovery.getUserName(),
                    recovery.getRecoveryQuantity(),
                    recovery.getRecoveryTotalPrice(),
                    recovery.getRecoveryDate()
            );
            sendResponse("Recovery updated successfully");
        } catch (Exception e) {
            sendResponse("Error updating recovery: " + e.getMessage());
        }
    }

    private void sendResponse(String message) throws IOException {
        output.writeObject(message);
        output.flush();
    }

    private void cleanup() {
        running = false;
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error cleaning up client handler", e);
        }
    }
}