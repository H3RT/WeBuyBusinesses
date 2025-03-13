package com.example.WeBuyBusinesses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Connect to the SQLite database (it will create the database if it doesn't exist)
            String url = "jdbc:sqlite:userdata.db";
            conn = DriverManager.getConnection(url);

             System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
