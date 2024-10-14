/*package controller;

import model.Report; // Assuming you have a Report class to structure the report data
import model.Sell;
import model.Recovery;
import java.util.ArrayList;
import java.util.List;

public class ReportController {
    private final sellController salesController; // Instance of sales controller to access sales data
    private final recoveryController recoveryController; // Instance of recovery controller to access recovery data

    // Constructor to initialize controllers
    public ReportController(sellController salesController, recoveryController recoveryController) {
        this.salesController = salesController;
        this.recoveryController = recoveryController;
    }

    // Method to generate a report of all sales
    public List<Report> generateSalesReport() {
        List<Report> reportList = new ArrayList<>();

        for (Sell sell : salesController.getAllSales()) {
            reportList.add(new Report(
                    1, // Report type (e.g., 1 for Sales)
                    "Sell",
                    sell.getItemName(),
                    sell.getItemPrice(),
                    sell.getUserName(), // Ensure User has a getUsername() method
                    sell.getSellQuantity(),
                    sell.getSellTotalPrice(),
                    sell.getSellDate().toString()
            ));
        }
        return reportList; // Return the sales report list
    }

    // Method to generate a report of all recoveries
    public List<Report> generateRecoveryReport() {
        List<Report> reportList = new ArrayList<>();

        for (Recovery recovery : recoveryController.getAllRecoveries()) {
            reportList.add(new Report(
                    2, // Report type (e.g., 2 for Recoveries)
                    "Recovery",
                    recovery.getRecoveryID(),
                    recovery.getItemName(),
                    recovery.getUserName(), // Ensure User has a getUsername() method
                    recovery.getRecoveryQuantity(),
                    recovery.getRecoveryTotalPrice(),
                     recovery.getRecoveryDate()
            ));
        }
        return reportList; // Return the recovery report list
    }

    // Method to print the reports to the console (optional)
    public void printReports(List<Report> reports) {
        for (Report report : reports) {
            System.out.println(report); // Ensure Report class has a suitable toString() method
        }
    }
}
*/

