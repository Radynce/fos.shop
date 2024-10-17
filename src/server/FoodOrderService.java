package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FoodOrderService extends Remote {
    void registerCustomer(String name, String email, int ICNumber) throws RemoteException;
    void placeOrder(String customerId, String foodItem) throws RemoteException;
    double calculateBill(String customerId) throws RemoteException;
}