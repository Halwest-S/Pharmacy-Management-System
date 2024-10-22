package model;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String username; // should match your getter method name
    private String password;
    private String role;

    // Constructor
    public User(int userID, String username, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getUserName() { return username; } // Correct method name
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }



}
