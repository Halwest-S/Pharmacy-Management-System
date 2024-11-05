package controller;

import DAO.SellDAO;
import Util.FileUtil;
import model.Item;
import model.Sell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



public class sellController {
    private SellDAO sellDAO;

    public sellController() {
        sellDAO = new SellDAO();
    }

    public String addSell(Sell sell) {
        if (sellDAO.getSellById(sell.getSellID()) != null) {
            return "Sale with ID " + sell.getSellID() + " already exists.";
        }
        return sellDAO.addSell(sell);
    }

    public String removeSell(int sellID) {
        if (sellDAO.getSellById(sellID) == null) {
            return "Sale with ID " + sellID + " not found.";
        }
        return sellDAO.removeSell(sellID);
    }

    public Sell getSellById(int sellID) {
        return sellDAO.getSellById(sellID);
    }

    public ArrayList<Sell> getAllSells() {
        return sellDAO.getAllSells();
    }

    public String updateSell(int sellID, String itemName, double itemPrice,
                             String userName, int sellQuantity,
                             double sellTotalPrice, Date sellDate) {
        Sell sell = sellDAO.getSellById(sellID);
        if (sell == null) {
            return "Sale with ID " + sellID + " not found.";
        }

        sell.setItemName(itemName);
        sell.setItemPrice(itemPrice);
        sell.setUserName(userName);
        sell.setSellQuantity(sellQuantity);
        sell.setSellTotalPrice(sellTotalPrice);
        sell.setSellDate(sellDate);

        return sellDAO.updateSell(sell);
    }
}