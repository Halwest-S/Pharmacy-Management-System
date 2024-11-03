package view;

import controller.Client.Client;
import controller.itemController;
import controller.recoveryController;
import controller.sellController;
import model.Item;
import model.Recovery;
import model.Sell;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeView {
    private final Scanner scanner = new Scanner(System.in);
    private final itemController itemController = new itemController();
    private final sellController sellController = new sellController();
    private final recoveryController recoveryController = new recoveryController();

    public void display() {
        while (true) {
            try {
                System.out.println("\nEmployee Menu");
                System.out.println("1. Access Item Info");
                System.out.println("2. Handle Sales or Recovery");
                System.out.println("3. Classify Item");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> accessItemInfo();
                    case 2 -> handleSalesOrRecovery();
                    case 3 -> classifyItem();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());

            }
        }
    }

    private void accessItemInfo() {

        System.out.println("Accessing Item Info...");
        try {
            ArrayList<Item> items = controller.itemController.getAllItems();
            if (items.isEmpty()) {
                System.out.println("No items available.");
                return;

            }else {
                System.out.println("\n==============================" +
                        "\n          ITEM DETAILS         " +
                        "\n==============================" );
            }
            for (Item item : items) {
                System.out.println(item);
            }

        } catch (Exception e) {
            System.out.println("Failed to access item info: " + e.getMessage());
        }
    }

    private void handleSalesOrRecovery() {
        System.out.println("Do you want to handle a Sale or a Recovery?");
        System.out.println("1. Sale");
        System.out.println("2. Recovery");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> handleSales();
                case 2 -> handleRecoveries();
                default -> System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void handleSales() {
        System.out.println("1. Add Sale");
        System.out.println("2. Remove Sale");
        System.out.println("3. Update Sale");
        System.out.println("4. List All Sales");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addSale();
                case 2 -> removeSale();
                case 3 -> updateSale();
                case 4 -> listSales();
                default -> System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void addSale() {
        try {
            System.out.print("Enter Sale ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Item Name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter Item Price: ");
            double itemPrice = scanner.nextDouble();

            System.out.print("Enter User Name: ");
            scanner.nextLine(); // Consume newline
            String userName = scanner.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            double totalPrice = itemPrice * quantity;

            // Create a new Sell object with the gathered data
            Sell sell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());

            // Assuming you have a method in your Client to handle the sale, send it to the server
            String response = Client.addSell(sell); // Call the method to send the sell to the server
            System.out.println(response != null ? response : "Failed to add sale.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void removeSale() {
        try {
            System.out.print("Enter Sale ID to remove: ");
            int id = scanner.nextInt(); // Get the Sale ID from user input
            String response = Client.removeSell(id);  // Use Client to send request to server
            System.out.println(response != null ? response : "Failed to remove sale."); // Print response from server
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number."); // Handle invalid input
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage()); // Handle other exceptions
        }
    }


    private void updateSale() {
        try {
            System.out.print("Enter Sale ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Item Name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter new Item Price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new User Name: ");
            String userName = scanner.nextLine();

            System.out.print("Enter new Quantity: ");
            int quantity = scanner.nextInt();
            double totalPrice = itemPrice * quantity;
            scanner.nextLine(); // Consume newline



            // Create an updated sale object with new details
            Sell updatedSell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());

            // Update the sale in the controller
            String response = Client.updateSell(updatedSell); // Assuming Client sends the update request to the server
            System.out.println(response != null ? response : "Failed to update sale.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void listSales() {
        try {
            System.out.println("Requesting updated list of sales from server...");
            ArrayList<Sell> sales = Client.getAllSells(); // Use Client to fetch sales from the server

            if (sales == null || sales.isEmpty()) {
                System.out.println("No sales available.");
            } else {
                System.out.println("\n**********************************" +
                        "\n           SELL DETAILS           " +
                        "\n**********************************" );
                for (Sell sell : sales) {
                    System.out.println(sell); // Print each sale
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while listing sales: " + e.getMessage());
        }
    }


    private void handleRecoveries() {
        System.out.println("1. Add Recovery");
        System.out.println("2. Remove Recovery");
        System.out.println("3. Update Recovery");
        System.out.println("4. List All Recoveries");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addRecovery();
                case 2 -> removeRecovery();
                case 3 -> updateRecovery();
                case 4 -> listRecoveries();
                default -> System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void addRecovery() {
        try {
            System.out.print("Enter Recovery ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Item Name: ");
            String itemName = scanner.nextLine();
            System.out.print("Enter Item Price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter User Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            double totalPrice = itemPrice * quantity;
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Recovery Date: ");
            String recoveryDate = scanner.nextLine();

            Recovery recovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
            String response = Client.addRecovery(recovery); // Use Client to send request to server
            System.out.println(response != null ? response : "Failed to add recovery.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void removeRecovery() {
        try {
            System.out.print("Enter Recovery ID to remove: ");
            int id = scanner.nextInt();
            String response = Client.removeRecovery(id); // Use Client to send request to server
            System.out.println(response != null ? response : "Failed to remove recovery.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void updateRecovery() {
        try {
            System.out.print("Enter Recovery ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new Item Name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter new Item Price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new User Name: ");
            String userName = scanner.nextLine();

            System.out.print("Enter new Quantity: ");
            int quantity = scanner.nextInt();
            double totalPrice = itemPrice * quantity;
            scanner.nextLine(); // Consume newline



            // Create an updated recovery object with new details
            Recovery updatedRecovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());

            // Update the recovery in the controller
            String response = Client.updateRecovery(updatedRecovery); // Assuming Client sends the update request to the server
            System.out.println(response != null ? response : "Failed to update recovery.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void listRecoveries() {
        try {
            System.out.println("Requesting updated list of recoveries from server...");
            ArrayList<Recovery> recoveries = Client.getAllRecoveries(); // Use Client to fetch recoveries from the server

            if (recoveries == null || recoveries.isEmpty()) {
                System.out.println("No recoveries available.");
            } else {
                System.out.println("\n----------------------------------" +
                        "\n          RECOVERY DETAILS        " +
                        "\n----------------------------------");
                for (Recovery recovery : recoveries) {
                    System.out.println(recovery); // Print each recovery
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while listing recoveries: " + e.getMessage());
        }
    }

    private void classifyItem() {
        System.out.println("Classifying Item...");

    }
}
