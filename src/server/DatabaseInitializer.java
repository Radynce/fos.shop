package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String URL = "jdbc:postgresql://localhost:5432/fos";
    private static final String USER = "tj"; // Replace with your username
    private static final String PASSWORD = "pwd"; // Replace with your password

    public static void createTables() {
        String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "email VARCHAR(100)," +
                "ICNumber INT" +
                ");";

        String createOrdersTable = "CREATE TABLE IF NOT EXISTS orders (" +
                "id SERIAL PRIMARY KEY, " +
                "customer_id INT REFERENCES customers(id), " +
                "food_item VARCHAR(100), " +
                "order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {

            stmt.execute(createCustomersTable);
            stmt.execute(createOrdersTable);

            System.out.println("Tables created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}