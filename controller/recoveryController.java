package controller;

import model.Recovery;

import java.util.ArrayList;

public class recoveryController {
    private final ArrayList<Recovery> recoveryList = new ArrayList<>();

    // Add a new recovery
    public static void addRecovery(Recovery recovery) {
        recoveryList.add(recovery);
    }

    // Remove a recovery by ID
    public static void removeRecovery(int id) {
        recoveryList.removeIf(recovery -> recovery.getRecoveryID() == id);
    }

    // Get a recovery by ID
    public Recovery getRecoveryById(int id) {
        for (Recovery recovery : recoveryList) {
            if (recovery.getRecoveryID() == id) {
                return recovery;
            }
        }
        return null; // Recovery not found
    }

    // Get all recoveries
    public static ArrayList<Recovery> getAllRecoveries() {
        return new ArrayList<>(recoveryList);
    }

    // Update a recovery
    public static void updateRecovery(int id, Recovery updatedRecovery) {
        for (int i = 0; i < recoveryList.size(); i++) {
            if (recoveryList.get(i).getRecoveryID() == id) {
                recoveryList.set(i, updatedRecovery);
                return; // Recovery updated successfully
            }
        }
    }
}
