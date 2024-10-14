package view;

import java.util.Scanner;

public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);

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
}

