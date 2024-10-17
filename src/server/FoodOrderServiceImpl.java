package server;

import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
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
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'registerCustomer'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void placeOrder(String customerId, String foodItem) throws RemoteException {
        // Code to place order in the database
    }

    @Override
    public double calculateBill(String customerId) throws RemoteException {
        // Code to calculate bill from orders in the database
        return 0.0; // Placeholder
    }

}