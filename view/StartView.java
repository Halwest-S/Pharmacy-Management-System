package view;

import java.util.Scanner;

public class StartView {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Welcome to Pharmacy Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Here you would typically validate the credentials
        // For now, we'll just assume it's valid and show the appropriate view
        if (username.equals("employee")) {
            new EmployeeView().display();
        } else if (username.equals("manager")) {
            new ManagerView().display();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}
