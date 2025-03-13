package com.example.WeBuyBusinesses;

import java.sql.*;

public class Buy {

    private String buyerName;
    private double buyerBudget;
    private boolean purchaseStatus; // False = Not purchased, True = Purchased

    public Buy(String buyerName, double buyerBudget) {
        this.buyerName = buyerName;
        this.buyerBudget = buyerBudget;
        this.purchaseStatus = false;
    }


    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getBuyerBudget() {
        return buyerBudget;
    }

    public void setBuyerBudget(double buyerBudget) {
        this.buyerBudget = buyerBudget;
    }

    public boolean getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(boolean purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }


    public void attemptPurchase(Sell business) {
        if (this.buyerBudget >= business.getPrice()) {
            this.purchaseStatus = true; 
            System.out.println("Congratulations " + buyerName + ", you have successfully purchased the business: " + business.getBname());

            storeBuyerData();
        } else {
            System.out.println("Sorry " + buyerName + ", you don't have enough budget to purchase the business: " + business.getBname());
        }
    }


    public void displayBuyerInfo() {
        System.out.println("========== Buyer Info =========");
        System.out.println("Buyer Name: " + getBuyerName());
        System.out.println("Buyer Budget: R" + getBuyerBudget());
        System.out.println("Purchase Status: " + (getPurchaseStatus() ? "Purchased" : "Not Purchased"));
    }


    private void storeBuyerData() {
        try (Connection conn = SQLConnection.connect()) {
            String sql = "INSERT INTO buyData (buyer_name, buyer_budget, purchase_status) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, buyerName);
                pstmt.setDouble(2, buyerBudget);
                pstmt.setBoolean(3, purchaseStatus);
                pstmt.executeUpdate();
                System.out.println("Buyer data stored in the 'buyData' table.");
            }
        } catch (SQLException e) {
            System.out.println("Error storing buyer data: " + e.getMessage());
        }
    }


    public static Sell checkBusinessExists(String businessName) {
        Sell business = null;

        try (Connection conn = SQLConnection.connect()) {
            String sql = "SELECT * FROM sellData WHERE business_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, businessName);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("seller_name");
                    String bname = rs.getString("business_name");
                    double price = rs.getDouble("price");
                    String regCode = rs.getString("registration_code");
                    int employees = rs.getInt("employees");
                    int customerStats = rs.getInt("customer_stats");
                    String location = rs.getString("location");
                    String description = rs.getString("description");


                    business = new Sell(name, bname, price, employees, regCode, customerStats, location, description);

                    //
                    System.out.println("Business found: ");
                    System.out.println("Business Name: " + business.getBname());
                    System.out.println("Business Price: R" + business.getPrice());
                    System.out.println("Business Description: " + business.getDescription());
                } else {
                    System.out.println("No business found with the name: " + businessName);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking business existence: " + e.getMessage());
        }

        return business;
    }

}
