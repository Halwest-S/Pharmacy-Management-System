package controller;

import model.Manager;

import java.util.ArrayList;

public class managerController {
    private final ArrayList<Manager> managerList = new ArrayList<>();

    // Add manager if not already in the list
    public void addManager(Manager manager) {
        if (getManagerById(manager.getManagerID()) == null) {
            managerList.add(manager);
        } else {
            System.out.println("Manager with ID " + manager.getManagerID() + " already exists.");
        }
    }

    // Remove manager by ID
    public void removeManager(int id) {
        managerList.removeIf((Manager manager) -> manager.getManagerID() == id);
    }

    // Get manager by ID
    public Manager getManagerById(int id) {
        for (Manager manager : managerList) {
            if (manager.getManagerID() == id) {
                return manager;
            }
        }
        return null;
    }

    // Get all managers
    public ArrayList<Manager> getAllManagers() {
        return managerList;
    }

    // Update manager details by ID
    public void updateManager(int id, String managerName, String managerPassword) {
        Manager manager = getManagerById(id);
        if (manager != null) {
            manager.setManagerName(managerName);
            manager.setManagerPassword(managerPassword);
        } else {
            System.out.println("Manager with ID " + id + " not found.");
        }
    }
}
