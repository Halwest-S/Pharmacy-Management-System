package model;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 4503646276650372829L;
    private int itemID;
    private String scientificName;
    private String commonName;
    private String company;
    private String country;
    private String category;
    private double importPrice;
    private double exportPrice;
    private int quantity;
    private String importDate;
    private String expiryDate;

    public Item(int itemID, String scientificName, String commonName, String company, String country, String category,
                double importPrice, double exportPrice, int quantity, String importDate, String expiryDate) {
        this.itemID = itemID;
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.company = company;
        this.country = country;
        this.category = category;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.quantity = quantity;
        this.importDate = importDate;
        this.expiryDate = expiryDate;
    }

    public Item() {

    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    @Override
    public String toString() {
        return
                "\nItem ID         : " + itemID +
                "\nScientific Name : " + scientificName +
                "\nCommon Name     : " + commonName +
                "\nCompany         : " + company +
                "\nCountry         : " + country +
                "\nCategory        : " + category +
                "\nImport Price    : $" + String.format("%.2f", importPrice) +
                "\nExport Price    : $" + String.format("%.2f", exportPrice) +
                "\nQuantity        : " + quantity +
                "\nImport Date     : " + importDate +
                "\nExpiry Date     : " + expiryDate +
                "\n==============================";
    }


}
