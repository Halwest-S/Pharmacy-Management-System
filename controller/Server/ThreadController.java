package controller.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import controller.itemController;
import model.Item;

public class ThreadController extends Thread {
    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private itemController itemController; // Reference to the item controller

    public ThreadController(Socket socket) {
        this.socket = socket;
        this.itemController = new itemController();
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
                    case "addItem":
                        Item item = (Item) inStream.readObject();
                        System.out.println("Received item to add: " + item);
                        itemController.addItem(item);
                        outStream.writeObject("Item added successfully.");
                        System.out.println("Item added and response sent to client.");
                        break;


                    case "removeItem":
                        System.out.println("Processing 'removeItem' command...");
                        int removeId = (int) inStream.readObject();
                        System.out.println("Received ID to remove: " + removeId);
                        itemController.removeItem(removeId);
                        outStream.writeObject("Item removed successfully (if it existed).");
                        System.out.println("Item removed and response sent.");
                        break;


                    case "getItemById":
                        int id = (int) inStream.readObject();
                        System.out.println("Received ID to retrieve: " + id);
                        Item foundItem = itemController.getItemById(id);
                        outStream.writeObject(foundItem);
                        System.out.println("Item retrieved and response sent.");
                        break;

                    case "getAllItems":
                        System.out.println("Processing getAllItems request");
                        ArrayList<Item> items = itemController.getAllItems();
                        System.out.println("Returning items list to client: " + items);
                        outStream.writeObject(items);
                        break;




                    case "updateItem":
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
                        ); // Use itemController to update item
                        outStream.writeObject("Item updated successfully.");
                        System.out.println("Item updated and response sent.");
                        break;

                    default:
                        System.out.println("Unknown command received.");
                        outStream.writeObject("Unknown command.");
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