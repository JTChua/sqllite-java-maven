package com.tesdaciicc;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // SQLite will create this file if it doesn't exist
        String url = "jdbc:sqlite:test.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("✅ Connected to SQLite!");

            // Create a table
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)");
            System.out.println("✅ Table 'users' created.");

            // Insert data
            stmt.execute("INSERT INTO users (name) VALUES ('Alice')");
            stmt.execute("INSERT INTO users (name) VALUES ('Bob')");
            System.out.println("✅ Added sample data.");

            // Query data
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("❌ SQLite error: " + e.getMessage());
        }
    }
}
