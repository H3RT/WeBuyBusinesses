package com.example.WeBuyBusinesses;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    public static void signUpUser(String username, String email, String password) {

        if (userExists(username, email)) {
            System.out.println("Username or email already taken.");
            return;
        }



        String sql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";

        try (Connection conn = SQLConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // Note: Hash the password in a real application
            pstmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean userExists(String username, String email) {
        String sql = "SELECT id FROM users WHERE username = ? OR email = ?";

        try (Connection conn = SQLConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // If any row exists, return true (user exists)
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

