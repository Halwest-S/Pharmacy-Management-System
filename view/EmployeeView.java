package view;

import controller.itemController;
import controller.recoveryController;
import controller.sellController;
import model.Item;
import model.Recovery;
import model.Sell;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private itemController itemController = new itemController();

    public void display() {
        while (true) {
            System.out.println("\nEmployee Menu");
            System.out.println("1. Access Item Info");
            System.out.println("2. Handle Sales Or recovery");
            System.out.println("3. Classify Item");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    accessItemInfo();
                    break;
                case 2:
                    handleSalesOrRecovery();
                    break;
                case 3:
                    classifyItem();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Access item info logic
    private void accessItemInfo() {
        System.out.println("Accessing Item Info...");
        ArrayList<Item> items = itemController.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        for (Item item : items) {
            System.out.println(item); // Ensure Item class has a proper toString method
        }
    }

    private void handleSalesOrRecovery() {
        System.out.println("Do you want to handle a Sale or a Recovery?");
        System.out.println("1. Sale");
        System.out.println("2. Recovery");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                handleSales();
                break;
            case 2:
                handleRecoveries();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void handleSales() {
        System.out.println("1. Add Sale");
        System.out.println("2. Remove Sale");
        System.out.println("3. Update Sale");
        System.out.println("4. List All Sales");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addSale();
                break;
            case 2:
                removeSale();
                break;
            case 3:
                updateSale();
                break;
            case 4:
                listSales();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addSale() {
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
        double totalPrice = itemPrice*quantity;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Sale Date: ");
        String sellDate = scanner.nextLine();

        Sell sell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        sellController.addSell(sell);
        System.out.println("Sale added successfully.");
    }

    private void removeSale() {
        System.out.print("Enter Sale ID to remove: ");
        int id = scanner.nextInt();
        sellController.removeSell(id);
        System.out.println("Sale removed successfully (if it existed).");
    }

    private void updateSale() {
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

        double totalPrice = itemPrice*quantity;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Sale Date: ");
        String sellDate = scanner.nextLine();

        Sell updatedSell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        sellController.updateSale(id, updatedSell);
        System.out.println("Sale updated successfully.");
    }

    private void listSales() {
        ArrayList<Sell> sales = sellController.getAllSell();
        if (sales.isEmpty()) {
            System.out.println("No sales available.");
        } else {
            for (Sell sell : sales) {
                System.out.println(sell);
            }
        }
    }

    private void handleRecoveries() {
        System.out.println("1. Add Recovery");
        System.out.println("2. Remove Recovery");
        System.out.println("3. Update Recovery");
        System.out.println("4. List All Recoveries");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addRecovery();
                break;
            case 2:
                removeRecovery();
                break;
            case 3:
                updateRecovery();
                break;
            case 4:
                listRecoveries();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addRecovery() {
        System.out.print("Enter Recovery ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Item Price: ");
        String itemPrice = scanner.nextLine();
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Total Price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Recovery Date: ");
        String recoveryDate = scanner.nextLine();

        Recovery recovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, recoveryDate);
        recoveryController.addRecovery(recovery);
        System.out.println("Recovery added successfully.");
    }

    private void removeRecovery() {
        System.out.print("Enter Recovery ID to remove: ");
        int id = scanner.nextInt();
        recoveryController.removeRecovery(id);
        System.out.println("Recovery removed successfully (if it existed).");
    }

    private void updateRecovery() {
        System.out.print("Enter Recovery ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter new Item Price: ");
        String itemPrice = scanner.nextLine();
        System.out.print("Enter new User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new Total Price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter new Recovery Date: ");
        String recoveryDate = scanner.nextLine();

        Recovery updatedRecovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, recoveryDate);
        recoveryController.updateRecovery(id, updatedRecovery);
        System.out.println("Recovery updated successfully.");
    }

    private void listRecoveries() {
        ArrayList<Recovery> recoveries = recoveryController.getAllRecoveries();
        if (recoveries.isEmpty()) {
            System.out.println("No recoveries available.");
        } else {
            for (Recovery recovery : recoveries) {
                System.out.println(recovery);
            }
        }
    }


    private void classifyItem() {
        System.out.println("Classifying Item...");
        // Implement item classification logic
    }
}
