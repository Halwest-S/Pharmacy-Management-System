package view;

import controller.employeeController;
import controller.itemController;
import controller.sellController;
import controller.recoveryController;
import model.Employee;
import model.Item;
import model.Sell;
import model.Recovery;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ManagerView {
    private Scanner scanner = new Scanner(System.in);
    private itemController itemController = new itemController();
    private employeeController employeeController = new employeeController();

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
        ArrayList<Item> items = itemController.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    listItems();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addItem() {
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
        itemController.addItem(newItem);
        System.out.println("Item added successfully.");
    }

    private void removeItem() {
        System.out.print("Enter Item ID to remove: ");
        int id = scanner.nextInt();
        itemController.removeItem(id);
        System.out.println("Item removed successfully (if it existed).");
    }

    private void updateItem() {
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
    }

    private void listItems() {
        ArrayList<Item> items = itemController.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    listEmployees();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEmployee() {
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
    }

    private void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();
        employeeController.removeEmployee(id);
        System.out.println("Employee removed successfully (if it existed).");
    }

    private void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Employee Password: ");
        String password = scanner.nextLine();

        employeeController.updateEmployee(id, name, password);
        System.out.println("Employee updated successfully.");
    }

    private void listEmployees() {
        ArrayList<Employee> employees = employeeController.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void generateReport() {
        System.out.println("Generating Report...");

    }
}
