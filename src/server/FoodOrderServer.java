package server;

import java.rmi.Naming;

public class FoodOrderServer {
    public static void main(String[] args) {
        try {
            FoodOrderService service = new FoodOrderServiceImpl();
            Naming.rebind("FoodOrderService", service);
            System.out.println("Food Order Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}