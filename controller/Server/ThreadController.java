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
        this.itemController = new itemController(); // Initialize item controller
    }

    public void run() {
        try {
            System.out.println("Client Connected");
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String command = (String) inStream.readObject(); // Read the command
                System.out.println("Received command: " + command); // Debug

                switch (command) {
                    case "addItem":
                        Item item = (Item) inStream.readObject(); // Receive the item object from the client
                        System.out.println("Received item to add: " + item); // Debug
                        itemController.addItem(item);  // Add the item using itemController
                        outStream.writeObject("Item added successfully."); // Send acknowledgment to the client
                        System.out.println("Item added and response sent to client."); // Debug
                        break;


                    case "removeItem":
                        int removeId = (int) inStream.readObject();
                        System.out.println("Received ID to remove: " + removeId); // Debug
                        itemController.removeItem(removeId); // Use itemController to remove item
                        outStream.writeObject("Item removed successfully (if it existed).");
                        System.out.println("Item removed and response sent."); // Debug
                        break;

                    case "getItemById":
                        int id = (int) inStream.readObject();
                        System.out.println("Received ID to retrieve: " + id); // Debug
                        Item foundItem = itemController.getItemById(id); // Use itemController to get item by ID
                        outStream.writeObject(foundItem); // Send item back to client
                        System.out.println("Item retrieved and response sent."); // Debug
                        break;

                    case "getAllItems":
                        System.out.println("Processing getAllItems request"); // Debug
                        ArrayList<Item> items = itemController.getAllItems(); // Fetch items from itemController
                        System.out.println("Returning items list to client: " + items); // Debug
                        outStream.writeObject(items);  // Send items list back to client
                        break;




                    case "updateItem":
                        Item updatedItem = (Item) inStream.readObject();
                        System.out.println("Received item to update: " + updatedItem); // Debug
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
                        System.out.println("Item updated and response sent."); // Debug
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
