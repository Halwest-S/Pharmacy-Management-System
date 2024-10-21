package view;

import controller.itemController;
import model.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private itemController itemController = new itemController();

    public void display() {
        while (true) {
            System.out.println("\nEmployee Menu");
            System.out.println("1. Access Item Info");
            System.out.println("2. Handle Sales");
            System.out.println("3. Classify Item");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    accessItemInfo();
                    break;
                case 2:
                    handleSales();
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

    private void handleSales() {
        System.out.println("Handling Sales...");
        // Implement sales handling logic
    }

    private void classifyItem() {
        System.out.println("Classifying Item...");
        // Implement item classification logic
    }
}
