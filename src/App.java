//this will be a file to test the backend only not the UI because the UI will be a graphical user interface
import model.*;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Food Delivery System Starting...");
            
            // Test MenuItems
            System.out.println("\n=== Testing MenuItems ===");
            MenuItems burger = new MenuItems("M001", "Cheeseburger", 8.99, "Main");
            System.out.println("Menu item created: " + burger);
            
            MenuItems fries = new MenuItems("M002", "French Fries", 3.99, "Side");
            System.out.println("Menu item created: " + fries);
            
            MenuItems soda = new MenuItems("M003", "Soda", 1.99, "Drink");
            System.out.println("Menu item created: " + soda);
            
            // Test negative price handling
            try {
                MenuItems invalidItem = new MenuItems("M004", "Invalid Item", -5.00, "Test");
                System.out.println("This should not print if validation works properly");
            } catch (ArithmeticException e) {
                System.out.println("Validation correctly caught negative price: " + e.getMessage());
            }
            
            // Test Order functionality
            System.out.println("\n=== Testing Order ===");
            Order order = new Order("John Doe", "123 Main St", "555-1234");
            System.out.println("New order created");
            
            // Add items to order
            order.addItem(burger);
            order.addItem(fries);
            order.addItem(soda);
            
            // Display order details
            System.out.println("Customer: " + order.getCustomerName());
            System.out.println("Address: " + order.getCustomerAddress());
            System.out.println("Phone: " + order.getCustomerPhone());
            System.out.println("Order items:");
            
            List<MenuItems> items = order.getOrderItems();
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i+1) + ". " + items.get(i));
            }
            
            System.out.println("Total price: $" + order.getTotalPrice());
            
            // Test removing an item
            System.out.println("\n=== Testing Item Removal ===");
            System.out.println("Removing " + fries.getName());
            order.removeItem(fries);
            
            System.out.println("Updated order items:");
            items = order.getOrderItems();
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i+1) + ". " + items.get(i));
            }
            
            System.out.println("Updated total price: $" + order.getTotalPrice());
            
            // Test cancelling the order
            System.out.println("\n=== Testing Order Cancellation ===");
            boolean cancelled = order.cancelOrder();
            System.out.println("Order cancelled: " + cancelled);
            System.out.println("Items remaining in order: " + order.getOrderItems().size());
            System.out.println("Final total price: $" + order.getTotalPrice());
            
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
