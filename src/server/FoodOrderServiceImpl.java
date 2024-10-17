package server;

import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.rmi.RemoteException;

public class FoodOrderServiceImpl extends UnicastRemoteObject implements FoodOrderService {

    // db connection
    private Connection connect() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/fos";
        String user = "tj";
        String password = "pwd";
        return DriverManager.getConnection(url, user, password);
    }

    // Constructor
    protected FoodOrderServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void registerCustomer(String name, String email, int ICNumber) throws RemoteException {
        try {
            Connection conn = connect();
            String sql = "INSERT INTO customers (name, contact) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, ICNumber);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void placeOrder(String customerId, String foodItem) throws RemoteException {
        try (Connection conn = connect()) {
            String sql = "INSERT INTO orders (customer_id, food_item) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(customerId));
            pstmt.setString(2, foodItem);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateBill(String customerId) throws RemoteException {
        double total = 0.0;
        try (Connection conn = connect()) {
            String sql = "SELECT COUNT(*) AS item_count FROM orders WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(customerId));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int itemCount = rs.getInt("item_count");
                total = itemCount * 10; // Assuming each item costs $10
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

}