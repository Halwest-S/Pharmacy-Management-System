package controller;

import Util.FileUtil;
import model.Recovery;
import model.Sell;

import java.util.ArrayList;

public class recoveryController {
    private static ArrayList<Recovery> recoveryList;
    private static final String FILE_NAME = "recovery.txt";



    private static void loadRecovery() {
        recoveryList = (ArrayList<Recovery>) FileUtil.readFromFile(FILE_NAME);
        if (recoveryList == null) {

            recoveryList = new ArrayList<>();

        }
    }

    private static void saveRecovery() {
        FileUtil.writeToFile(FILE_NAME, recoveryList);
    }




    // Add a new recovery
    public static void addRecovery(Recovery recovery) {
        recoveryList.add(recovery);
        saveRecovery();
    }

    // Remove a recovery by ID
    public static void removeRecovery(int id) {
        recoveryList.removeIf(recovery -> recovery.getRecoveryID() == id);
        saveRecovery();
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
        loadRecovery();
        return recoveryList;
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
