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
                System.out.println(item); // Ensure Item class has a proper toString method
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
        scanner.nextLine(); // Consume newline
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        double totalPrice = itemPrice*quantity;


        Sell sell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        sellController.addSell(sell);
        System.out.println("Sale added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data .");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void removeSale() {
        try {
        System.out.print("Enter Sale ID to remove: ");
        int id = scanner.nextInt();
        sellController.removeSell(id);
        System.out.println("Sale removed successfully (if it existed).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
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

        double totalPrice = itemPrice*quantity;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Sale Date: ");
        String sellDate = scanner.nextLine();

        Sell updatedSell = new Sell(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        sellController.updateSale(id, updatedSell);
        System.out.println("Sale updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data .");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void listSales() {
        try {
        ArrayList<Sell> sales = sellController.getAllSell();
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
            System.out.println("Failed to List Sales: " + e.getMessage());
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

        double totalPrice = itemPrice*quantity;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Recovery Date: ");
        String recoveryDate = scanner.nextLine();

        Recovery recovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        recoveryController.addRecovery(recovery);
        System.out.println("Recovery added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data .");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void removeRecovery() {
        try {
        System.out.print("Enter Recovery ID to remove: ");
        int id = scanner.nextInt();
        recoveryController.removeRecovery(id);
        System.out.println("Recovery removed successfully (if it existed).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
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
        scanner.nextLine();
        System.out.print("Enter new Recovery Date: ");
        String recoveryDate = scanner.nextLine();

        Recovery updatedRecovery = new Recovery(id, itemName, itemPrice, userName, quantity, totalPrice, new Date());
        recoveryController.updateRecovery(id, updatedRecovery);
        System.out.println("Recovery updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data .");
            scanner.nextLine(); // Clear the invalid input
        }
        }

    private void listRecoveries() {
       try {
        ArrayList<Recovery> recoveries = recoveryController.getAllRecoveries();
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
           System.out.println("Failed to List Recoveries: " + e.getMessage());
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
        itemController.removeItem(id);
        System.out.println("Item removed successfully (if it existed).");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
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

        itemController.updateItem(id, scientificName, commonName, company, country, category, importPrice, exportPrice, quantity, importDate, expiryDate);
        System.out.println("Item updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data .");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void listItems() {
        try {
            ArrayList<Item> items = Client.getAllItems();  // Fetch items from the server

            if (items == null || items.isEmpty()) {
                System.out.println("No items available.");
<<<<<<< HEAD
            } else {
                System.out.println("\n==============================" +
                        "\n          ITEM DETAILS         " +
                        "\n==============================" );
                for (Item item : items) {
                    System.out.println(item);
                }
=======
>>>>>>> temp_branch
            }
                for (Item item : items) {
                    System.out.println(item);  // Display each item
                }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while listing items: " + e);  // More informative message
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
        employeeController.addEmployee(newEmployee);
        System.out.println("Employee added successfully.");
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
        employeeController.removeEmployee(id);
        System.out.println("Employee removed successfully (if it existed).");
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

        employeeController.updateEmployee(id, name, password);
        System.out.println("Employee updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void listEmployees() {
        try {
        ArrayList<Employee> employees = employeeController.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }else {
            System.out.println("\n==============================" +
                    "\n        EMPLOYEE DETAILS       " +
                    "\n==============================" );
        for (Employee employee : employees) {
            System.out.println(employee);
        }}
        } catch (Exception e) {
            System.out.println("An error occurred while listing employees: " + e.getMessage());
        }
    }

    private void generateReport() {
        System.out.println("Generating Report...");

    }
}
