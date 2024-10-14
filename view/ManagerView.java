package view;

import java.util.Scanner;

public class ManagerView {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\nManager Menu");
            System.out.println("1. Access Item Info");
            System.out.println("2. Handle Sales");
            System.out.println("3. Classify Item");
            System.out.println("4. Manage Items");
            System.out.println("5. Manage Employees");
            System.out.println("6. Generate Report");
            System.out.println("7. Logout");
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
                    manageItems();
                    break;
                case 5:
                    manageEmployees();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void accessItemInfo() {
        System.out.println("Accessing Item Info...");
        // Implement item info access logic
    }

    private void handleSales() {
        System.out.println("Handling Sales...");
        // Implement sales handling logic
    }

    private void classifyItem() {
        System.out.println("Classifying Item...");
        // Implement item classification logic
    }

    private void manageItems() {
        while (true) {
            System.out.println("\nManage Items");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. Back to Manager Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Adding Item...");
                    // Implement add item logic
                    break;
                case 2:
                    System.out.println("Removing Item...");
                    // Implement remove item logic
                    break;
                case 3:
                    System.out.println("Updating Item...");
                    // Implement update item logic
                    break;
                case 4:
                    System.out.println("Listing Items...");
                    // Implement list items logic
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageEmployees() {
        while (true) {
            System.out.println("\nManage Employees");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. List Employees");
            System.out.println("5. Back to Manager Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Adding Employee...");
                    // Implement add employee logic
                    break;
                case 2:
                    System.out.println("Removing Employee...");
                    // Implement remove employee logic
                    break;
                case 3:
                    System.out.println("Updating Employee...");
                    // Implement update employee logic
                    break;
                case 4:
                    System.out.println("Listing Employees...");
                    // Implement list employees logic
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void generateReport() {
        System.out.println("Generating Report...");
        // Implement report generation logic
    }
}
