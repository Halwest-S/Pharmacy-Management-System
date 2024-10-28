package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartView {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("Welcome to Pharmacy Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> login();
                    case 2 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void login() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Here you would typically validate the credentials
            // For now, we'll just assume it's valid and show the appropriate view
            if (username.equals("employee") & password.equals("password")) {
                new EmployeeView().display();
            } else if (username.equals("manager") & password.equals("password")) {
                new ManagerView().display();
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }
}
