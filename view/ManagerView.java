package view;

import controller.Client.Client;
import controller.employeeController;
import controller.itemController;
import controller.sellController;
import controller.recoveryController;
import model.Employee;
import model.Item;
import model.Sell;
import model.Recovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerView {
    private final Scanner scanner = new Scanner(System.in);
    private final itemController itemController = new itemController();
    private final employeeController employeeController = new employeeController();

    public void display() {
        while (true) {
            System.out.println("\nManager Menu");
            System.out.println("1. Access Item Info");
            System.out.println("2. Handle Sales or recovery");
            System.out.println("3. Classify Item");
            System.out.println("4. Manage Items");
            System.out.println("5. Manage Employees");
            System.out.println("6. Generate Report");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            try {


                switch (choice) {
                    case 1 -> accessItemInfo();
                    case 2 -> handleSalesOrRecovery();
                    case 3 -> classifyItem();
                    case 4 -> manageItems();
                    case 5 -> manageEmployees();
                    case 6 -> generateReport();
                    case 7 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
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


            Sell sell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());


            String response = Client.addSell(sell);
            System.out.println(response != null ? response : "Failed to add sale.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private void removeSale() {
        try {
            System.out.print("Enter Sale ID to remove: ");
            int id = scanner.nextInt();
            String response = Client.removeSell(id);
            System.out.println(response != null ? response : "Failed to remove sale.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
            scanner.nextLine();



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

    private void manageItems() {
        while (true) {
            System.out.println("\nManage Items");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item");
            System.out.println("4. List Items");
            System.out.println("5. Back to Manager Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addItem();
                    case 2 -> removeItem();
                    case 3 -> updateItem();
                    case 4 -> listItems();
                    case 5 -> {
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

    private void addItem() {
        try {
            System.out.print("Enter Item ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Scientific Name: ");
            String scientificName = scanner.nextLine();
            System.out.print("Enter Common Name: ");
            String commonName = scanner.nextLine();
            System.out.print("Enter Company: ");
            String company = scanner.nextLine();
            System.out.print("Enter Country: ");
            String country = scanner.nextLine();
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();
            System.out.print("Enter Import Price: ");
            double importPrice = scanner.nextDouble();
            System.out.print("Enter Export Price: ");
            double exportPrice = scanner.nextDouble();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Import Date: ");
            String importDate = scanner.nextLine();
            System.out.print("Enter Expiry Date: ");
            String expiryDate = scanner.nextLine();

            Item newItem = new Item(id, scientificName, commonName, company, country, category, importPrice, exportPrice, quantity, importDate, expiryDate);
            // Send to server only
            String response = Client.addItem(newItem);
            System.out.println(response != null ? response : "Failed to add item.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine();
        } catch (Exception e) {
            // System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void removeItem() {
        try {
            System.out.print("Enter Item ID to remove: ");
            int id = scanner.nextInt();
            String response = Client.removeItem(id);  // Use Client to send request to server
            System.out.println(response != null ? response : "Failed to remove item.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void updateItem() {
        try {
            System.out.print("Enter Item ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Scientific Name: ");
            String scientificName = scanner.nextLine();
            System.out.print("Enter new Common Name: ");
            String commonName = scanner.nextLine();
            System.out.print("Enter new Company: ");
            String company = scanner.nextLine();
            System.out.print("Enter new Country: ");
            String country = scanner.nextLine();
            System.out.print("Enter new Category: ");
            String category = scanner.nextLine();
            System.out.print("Enter new Import Price: ");
            double importPrice = scanner.nextDouble();
            System.out.print("Enter new Export Price: ");
            double exportPrice = scanner.nextDouble();
            System.out.print("Enter new Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Import Date: ");
            String importDate = scanner.nextLine();
            System.out.print("Enter new Expiry Date: ");
            String expiryDate = scanner.nextLine();

            Item updatedItem = new Item(id, scientificName, commonName, company, country, category, importPrice, exportPrice, quantity, importDate, expiryDate);
            String response = Client.updateItem(updatedItem);  // Use Client for server request
            System.out.println(response != null ? response : "Failed to update item.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void listItems() {
        try {
            System.out.println("Requesting updated list from server...");
            ArrayList<Item> items = Client.getAllItems();

            if (items == null || items.isEmpty()) {
                System.out.println("No items available.");
            } else {
                System.out.println("\n==============================" +
                        "\n          ITEM DETAILS         " +
                        "\n==============================");
                for (Item item : items) {
                    System.out.println(item);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while listing items: " + e);
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
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> removeEmployee();
                    case 3 -> updateEmployee();
                    case 4 -> listEmployees();
                    case 5 -> {
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

    private void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Employee Password: ");
            String password = scanner.nextLine();

            Employee newEmployee = new Employee(id, name, password);

            // Send addEmployee request to server
            String response = Client.addEmployee(newEmployee);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void removeEmployee() {
        try {
            System.out.print("Enter Employee ID to remove: ");
            int id = scanner.nextInt();

            // Send removeEmployee request to server
            String response = Client.removeEmployee(id);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        try {
            System.out.print("Enter Employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Employee Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Employee Password: ");
            String password = scanner.nextLine();

            Employee updatedEmployee = new Employee(id, name, password);

            // Send updateEmployee request to server
            String response = Client.updateEmployee(updatedEmployee);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void listEmployees() {
        try {
            // Request all employees from server
            ArrayList<Employee> employees = Client.getAllEmployees();

            if (employees.isEmpty()) {
                System.out.println("No employees available.");
            } else {
                System.out.println("\n===============================" +
                        "\n         EMPLOYEE DETAILS       " +
                        "\n===============================");
                for (Employee employee : employees) {
                    System.out.println(employee);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while listing employees: " + e.getMessage());
        }
    }


    private void generateReport() {
        System.out.println("Generating Report...");

    }
}