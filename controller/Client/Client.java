package controller.Client;

import model.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static Socket socket;
    public static ObjectInputStream objIn;
    public static ObjectOutputStream objOut;

    public static void startConnection() {
        try {
            socket = new Socket("localhost", 5000);
            objOut = new ObjectOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(socket.getInputStream());

            System.out.println("---- Successfully connected to server ----");
        } catch (IOException e) {
            System.err.println("Initializing stream failed" + e.getMessage());
        }
    }

    public static void stopConnection() {
        try {
            objIn.close();
            objOut.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Stopping connection was failed" + e.getMessage());
        }
    }
    public static String addItem(Item item) throws IOException, ClassNotFoundException {
        System.out.println("Sending 'addItem' command to server..."); // Debug
        objOut.writeObject("addItem");  // Send command
        objOut.writeObject(item);       // Send item object
        System.out.println("Waiting for response from server..."); // Debug

        // Read response from server
        String response = (String) objIn.readObject();
        System.out.println("Received response: " + response); // Debug

        return response;
    }


    public static String removeItem(int id) throws IOException, ClassNotFoundException {
        objOut.writeObject("removeItem");
        objOut.writeObject(id);
        return (String) objIn.readObject();
    }

    public static Item getItemById(int id) throws IOException, ClassNotFoundException {
        objOut.writeObject("getItemById");
        objOut.writeObject(id);
        return (Item) objIn.readObject();
    }

    public static ArrayList<Item> getAllItems() throws IOException, ClassNotFoundException {
        System.out.println("Requesting all items from server..."); // Debug
        objOut.writeObject("getAllItems"); // Send command to server

        // Expecting to receive an ArrayList<Item> response from the server
        ArrayList<Item> items = (ArrayList<Item>) objIn.readObject();
        System.out.println("Received items list from server: " + items); // Debug
        return items;
    }



    public static String updateItem(Item item) throws IOException, ClassNotFoundException {
        objOut.writeObject("updateItem");
        objOut.writeObject(item);
        return (String) objIn.readObject();
    }
}