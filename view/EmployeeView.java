package view;

import controller.itemController;
import controller.recoveryController;
import controller.sellController;
import model.Item;
import model.Recovery;
import model.Sell;


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
                System.out.println(item); // Ensure Item class has a proper toString method
            }

        } catch (Exception e) {
            System.out.println("Failed to access item info: " + e.getMessage());
        }
    }

    private void handleSalesOrRecovery() {
        try {
            System.out.println("Do you want to handle a Sale or a Recovery?");
            System.out.println("1. Sale");
            System.out.println("2. Recovery");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> handleSales();
                case 2 -> handleRecoveries();
                default -> System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void handleSales() {
        try {
            System.out.println("1. Add Sale");
            System.out.println("2. Remove Sale");
            System.out.println("3. Update Sale");
            System.out.println("4. List All Sales");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addSale();
                case 2 -> removeSale();
                case 3 -> updateSale();
                case 4 -> listSales();
                default -> System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
            scanner.nextLine(); // Consume newline
            System.out.print("Enter User Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            double totalPrice = itemPrice * quantity;



            Sell sell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
            controller.sellController.addSell(sell);
            System.out.println("Sale added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to add sale: " + e.getMessage());
        }
    }

    private void removeSale() {
        try {
            System.out.print("Enter Sale ID to remove: ");
            int id = scanner.nextInt();
            controller.sellController.removeSell(id);
            System.out.println("Sale removed successfully (if it existed).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to remove sale: " + e.getMessage());
        }
    }

    private void updateSale() {
        try {
            System.out.print("Enter Sale ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new Item Name: ");
            String itemName = scanner.nextLine();
            System.out.print("Enter new Item Price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter new User Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter new Quantity: ");
            int quantity = scanner.nextInt();

            double totalPrice = itemPrice * quantity;
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Sale Date: ");
            String sellDate = scanner.nextLine();

            Sell updatedSell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
            controller.sellController.updateSale(id, updatedSell);
            System.out.println("Sale updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to update sale: " + e.getMessage());
        }
    }

    private void listSales() {
        try {
            ArrayList<Sell> sales = controller.sellController.getAllSell();
            if (sales.isEmpty()) {
                System.out.println("No sales available.");
            } else {
                System.out.println("\n**********************************" +
                        "\n           SELL DETAILS           " +
                        "\n**********************************" );
                for (Sell sell : sales) {
                    System.out.println(sell);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to list sales: " + e.getMessage());
        }
    }

    private void handleRecoveries() {
        try {
            System.out.println("1. Add Recovery");
            System.out.println("2. Remove Recovery");
            System.out.println("3. Update Recovery");
            System.out.println("4. List All Recoveries");
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
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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

            double totalPrice = itemPrice*quantity;


            Recovery recovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
            controller.recoveryController.addRecovery(recovery);
            System.out.println("Recovery added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to add recovery: " + e.getMessage());
        }
    }
    private void removeRecovery() {
        try {
            System.out.print("Enter Recovery ID to remove: ");
            int id = scanner.nextInt();
            controller.recoveryController.removeRecovery(id);
            System.out.println("Recovery removed successfully (if it existed).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to remove recovery: " + e.getMessage());
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
            System.out.print("Enter new User Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter new Quantity: ");
            int quantity = scanner.nextInt();

            double totalPrice = itemPrice*quantity;
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Recovery Date: ");
            String recoveryDate = scanner.nextLine();

            Recovery updatedRecovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
            controller.recoveryController.updateRecovery(id, updatedRecovery);
            System.out.println("Recovery updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("Failed to update recovery: " + e.getMessage());
        }
    }

    private void listRecoveries() {
        try {
            ArrayList<Recovery> recoveries = controller.recoveryController.getAllRecoveries();
            if (recoveries.isEmpty()) {
                System.out.println("No recoveries available.");
            } else {
                System.out.println("\n----------------------------------" +
                        "\n          RECOVERY DETAILS        " +
                        "\n----------------------------------" );
                for (Recovery recovery : recoveries) {
                    System.out.println(recovery);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to list recoveries: " + e.getMessage());
        }
    }

    private void classifyItem() {
        System.out.println("Classifying Item...");
        // Implement item classification logic
        // You might want to add exception handling here based on the implementation.
    }
}

