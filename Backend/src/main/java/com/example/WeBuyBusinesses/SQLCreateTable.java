package com.example.WeBuyBusinesses;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class SQLCreateTable {
    public static void createTable() {

        String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL UNIQUE, " +
                "email TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL" +
                ");";


        String createBuyDataTableSQL = "CREATE TABLE IF NOT EXISTS buyData (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "buyer_name TEXT NOT NULL, " +
                "buyer_budget REAL NOT NULL, " +
                "purchase_status BOOLEAN NOT NULL" +
                ");";

        String createSellDataTableSQL = "CREATE TABLE IF NOT EXISTS sellData (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "seller_name TEXT NOT NULL, " +
                "business_name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "registration_code TEXT NOT NULL, " +
                "employees INTEGER NOT NULL, " +
                "customer_stats INTEGER NOT NULL, " +
                "location TEXT NOT NULL, " +
                "description TEXT NOT NULL" +
                ");";


        try (Connection conn = SQLConnection.connect();
             Statement stmt = conn.createStatement()) {


            stmt.execute(createUsersTableSQL);
            System.out.println("Table 'users' created or already exists.");


            stmt.execute(createSellDataTableSQL);
            System.out.println("Table 'sellData' created or already exists.");


            stmt.execute(createBuyDataTableSQL);
            System.out.println("Table 'buyData' created or already exists.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
