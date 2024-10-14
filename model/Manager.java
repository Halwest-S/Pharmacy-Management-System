package model;

public class Manager {
    private int managerID;
    private String managerName;
    private String managerPassword;

    //constructor
    public Manager(int managerID, String managerName,String managerPassword) {
        this.managerID = managerID;
        this.managerName = managerName;
        this.managerPassword=managerPassword;
    }

    // Getters and Setters
    public int getManagerID() { return managerID; }
    public void setManagerID(int managerID) { this.managerID = managerID; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
}
