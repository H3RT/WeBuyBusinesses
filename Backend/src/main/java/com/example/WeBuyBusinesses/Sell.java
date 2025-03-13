package com.example.WeBuyBusinesses;

import java.sql.*;

public class Sell {

    private double price;
    private String Name;
    private String Bname;
    private String regCode;
    private int employees;
    private int customerStats;
    private String location;
    private String description;


    public Sell(String Name, String Bname, double price, int employees, String regCode, int customerStats, String location, String description) {
        this.Name = Name;
        this.Bname = Bname;
        this.price = price;
        this.regCode = regCode;
        this.employees = employees;
        this.customerStats = customerStats;
        this.location = location;
        this.description = description;
    }


    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }

    public String getBname() { return Bname; }
    public void setBname(String Bname) { this.Bname = Bname; }

    public int getCustomerStats() { return customerStats; }
    public void setCustomerStats(int customerStats) { this.customerStats = customerStats; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getEmployees() { return employees; }
    public void setEmployees(int employees) { this.employees = employees; }

    public String getRegCode() { return regCode; }
    public void setRegCode(String regCode) { this.regCode = regCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Input validation methods
    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s']+$");
    }

    public static boolean isValidRegCode(String regCode) {
        return regCode.matches("^[E][0-9]{9}$");
    }

    public static boolean isValidPrice(String priceInput) {
        return priceInput.matches("^R\\d+(\\.\\d{1,2})?$");
    }

    public static boolean isValidEmployees(int employees) {
        return employees > 0;
    }

    public static boolean isValidCustomerStats(double customerStats) {
        return customerStats >= 0 && customerStats <= 100;
    }

    public static boolean isValidLocation(String location) {
        return !location.isEmpty();
    }


    public void displayInfo() {
        System.out.println("============ Seller's Info =========");
        System.out.println("Name of seller: " + getName());
        System.out.println("Name of Business: " + getBname());
        System.out.println("Selling Price of business: R" + getPrice());
        System.out.println("Business registration code: " + getRegCode());
        System.out.println("Number of employees: " + getEmployees());
        System.out.println("Customer stats rate: " + getCustomerStats());
        System.out.println("Business is located at: " + getLocation());
        System.out.println("Business Description: " + getDescription());
    }


    public void insertIntoDatabase() {
        String url = "jdbc:sqlite:userdata.db";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {

            connection = DriverManager.getConnection(url);


            String sql = "INSERT INTO sellData(seller_name,business_name, price, registration_code, employees, customer_stats, location, description) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";


            stmt = connection.prepareStatement(sql);
            stmt.setString(1, getName());
            stmt.setString(2, getBname());
            stmt.setDouble(3, getPrice());
            stmt.setString(4, getRegCode());
            stmt.setInt(5, getEmployees());
            stmt.setInt(6, getCustomerStats());
            stmt.setString(7, getLocation());
            stmt.setString(8, getDescription());


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data successfully inserted into the database!");
            } else {
                System.out.println("Failed to insert data into the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
