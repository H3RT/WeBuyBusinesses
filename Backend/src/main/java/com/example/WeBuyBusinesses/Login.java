package com.example.WeBuyBusinesses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static boolean loginUser(String username, String password) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";

        try (Connection conn = SQLConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");


                if (storedPassword.equals(password)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("email not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

