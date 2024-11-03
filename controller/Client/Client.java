package controller.Client;

import model.Employee;
import model.Item;
import model.Recovery;
import model.Sell;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final int RETRY_DELAY_MS = 2000;

    private static Socket socket;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static boolean connected;

    public Client() {
        this.connected = false;
    }

    public boolean connect() {
        for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                socket = new Socket(HOST, PORT);
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());
                connected = true;
                LOGGER.info("Connected to server successfully");
                return true;
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Connection attempt " + attempt + " failed", e);
                if (attempt < MAX_RETRY_ATTEMPTS) {
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void disconnect() {
        if (!connected) return;

        try {
            connected = false;
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
            LOGGER.info("Disconnected from server");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error disconnecting from server", e);
        }
    }

    // Async operations for all entities


    // Item Operations
    public static String addItem(Item item) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("addItem");
        output.writeObject(item);
        return (String) input.readObject();
    }

    public static String removeItem(Integer itemId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("removeItem");
        output.writeObject(itemId);
        return (String) input.readObject();
    }

    public Item getItemById(Integer itemId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getItemById");
        output.writeObject(itemId);
        return (Item) input.readObject();
    }

    public static ArrayList<Item> getAllItems() throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getAllItems");
        return (ArrayList<Item>) input.readObject();
    }

    public static String updateItem(Item item) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("updateItem");
        output.writeObject(item);
        return (String) input.readObject();
    }

    // Employee Operations
    public static String addEmployee(Employee employee) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("addEmployee");
        output.writeObject(employee);
        return (String) input.readObject();
    }

    public static String removeEmployee(Integer employeeId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("removeEmployee");
        output.writeObject(employeeId);
        return (String) input.readObject();
    }

    public Employee getEmployeeById(Integer employeeId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getEmployeeById");
        output.writeObject(employeeId);
        return (Employee) input.readObject();
    }

    public static ArrayList<Employee> getAllEmployees() throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getAllEmployees");
        return (ArrayList<Employee>) input.readObject();
    }

    public static String updateEmployee(Employee employee) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("updateEmployee");
        output.writeObject(employee);
        return (String) input.readObject();
    }

    // Sell Operations
    public static String addSell(Sell sell) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("addSell");
        output.writeObject(sell);
        return (String) input.readObject();
    }

    public static String removeSell(Integer sellId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("removeSell");
        output.writeObject(sellId);
        return (String) input.readObject();
    }

    public Sell getSellById(Integer sellId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getSellById");
        output.writeObject(sellId);
        return (Sell) input.readObject();
    }

    public static ArrayList<Sell> getAllSells() throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getAllSells");
        return (ArrayList<Sell>) input.readObject();
    }

    public static String updateSell(Sell sell) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("updateSell");
        output.writeObject(sell);
        return (String) input.readObject();
    }

    // Recovery Operations
    public static String addRecovery(Recovery recovery) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("addRecovery");
        output.writeObject(recovery);
        return (String) input.readObject();
    }

    public static String removeRecovery(Integer recoveryId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("removeRecovery");
        output.writeObject(recoveryId);
        return (String) input.readObject();
    }

    public Recovery getRecoveryById(Integer recoveryId) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getRecoveryById");
        output.writeObject(recoveryId);
        return (Recovery) input.readObject();
    }

    public static ArrayList<Recovery> getAllRecoveries() throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("getAllRecoveries");
        return (ArrayList<Recovery>) input.readObject();
    }

    public static String updateRecovery(Recovery recovery) throws IOException, ClassNotFoundException {
        checkConnection();
        output.writeObject("updateRecovery");
        output.writeObject(recovery);
        return (String) input.readObject();
    }

    private static void checkConnection() throws IOException {
        if (!connected || socket.isClosed()) {
            throw new IOException("Not connected to server");
        }
    }

    // Example usage of the client

}