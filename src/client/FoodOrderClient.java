package client;

import server.FoodOrderService;
import java.rmi.Naming;
import java.util.Scanner;

public class FoodOrderClient {
    public static void main(String[] args) {
        try {
            FoodOrderService service = (FoodOrderService) Naming.lookup("rmi://localhost/FoodOrderService");
            Scanner scanner = new Scanner(System.in);

            // Register a new customer
            System.out.println("Register Customer:");
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter IC Number: ");
            int ICNumber = scanner.nextInt();
            service.registerCustomer(name, email, ICNumber);
            
            // Place an order
            System.out.print("Enter Customer ID to place an order: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter Food Item: ");
            String foodItem = scanner.nextLine();
            service.placeOrder(customerId, foodItem);

            // Calculate Bill
            double billAmount = service.calculateBill(customerId);
            System.out.println("Total Bill for Customer ID " + customerId + ": $" + billAmount);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}