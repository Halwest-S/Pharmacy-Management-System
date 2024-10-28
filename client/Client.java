// PharmacyClient.java
package client;

import java.io.*;
import java.net.*;
import model.*;
import java.util.ArrayList;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client() throws IOException {
        connect();
    }

    private void connect() throws IOException {
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public String login(String username, String password) throws IOException, ClassNotFoundException {
        out.writeObject("LOGIN|" + username + "|" + password);
        return (String) in.readObject();
    }

    public ArrayList<Item> getAllItems() throws IOException, ClassNotFoundException {
        out.writeObject("GET_ALL_ITEMS");
        return (ArrayList<Item>) in.readObject();
    }

    public String addItem(Item item) throws IOException, ClassNotFoundException {
        out.writeObject("ADD_ITEM");
        out.writeObject(item);
        return (String) in.readObject();
    }

    public String updateItem(Item item) throws IOException, ClassNotFoundException {
        out.writeObject("UPDATE_ITEM");
        out.writeObject(item);
        return (String) in.readObject();
    }

    public String removeItem(int itemId) throws IOException, ClassNotFoundException {
        out.writeObject("REMOVE_ITEM|" + itemId);
        return (String) in.readObject();
    }

    public String addEmployee(Employee employee) throws IOException, ClassNotFoundException {
        out.writeObject("ADD_EMPLOYEE");
        out.writeObject(employee);
        return (String) in.readObject();
    }

    public ArrayList<Employee> getAllEmployees() throws IOException, ClassNotFoundException {
        out.writeObject("GET_ALL_EMPLOYEES");
        return (ArrayList<Employee>) in.readObject();
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}