package controller;

import Util.FileUtil;
import model.Recovery;
import model.Sell;

import java.util.ArrayList;
import java.util.Date;


import DAO.RecoveryDAO;


public class recoveryController {
    private RecoveryDAO recoveryDAO;

    public recoveryController() {
        recoveryDAO = new RecoveryDAO();
    }

    public String addRecovery(Recovery recovery) {
        if (recoveryDAO.getRecoveryById(recovery.getRecoveryID()) != null) {
            return "Recovery with ID " + recovery.getRecoveryID() + " already exists.";
        }
        return recoveryDAO.addRecovery(recovery);
    }

    public String removeRecovery(int recoveryID) {
        if (recoveryDAO.getRecoveryById(recoveryID) == null) {
            return "Recovery with ID " + recoveryID + " not found.";
        }
        return recoveryDAO.removeRecovery(recoveryID);
    }

    public Recovery getRecoveryById(int recoveryID) {
        return recoveryDAO.getRecoveryById(recoveryID);
    }

    public ArrayList<Recovery> getAllRecoveries() {
        return recoveryDAO.getAllRecoveries();
    }

    public String updateRecovery(int recoveryID, String itemName, double itemPrice,
                                 String userName, int recoveryQuantity,
                                 double recoveryTotalPrice, Date recoveryDate) {
        Recovery recovery = recoveryDAO.getRecoveryById(recoveryID);
        if (recovery == null) {
            return "Recovery with ID " + recoveryID + " not found.";
        }

        recovery.setRecoveryID(recoveryID);
        recovery.setItem(itemName);
        recovery.setItemPrice(itemPrice);
        recovery.setUserName(userName);
        recovery.setRecoveryQuantity(recoveryQuantity);
        recovery.setRecoveryTotalPrice(recoveryTotalPrice);
        recovery.setRecoveryDate(recoveryDate);

        return recoveryDAO.updateRecovery(recovery);
    }
}