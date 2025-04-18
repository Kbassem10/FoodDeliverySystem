package com.fooddeliverysystem;

import java.util.List;
import java.util.Scanner;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.controller.SalesController;
import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;

/**
 * This class demonstrates the backend functionality of the Food Delivery System
 * without the JavaFX GUI.
 */
public class Main {
    
    private static MenuItemsController menuController = MenuItemsController.getInstance();
    private static OrderController orderController = OrderController.getInstance();
    private static SalesController salesController = SalesController.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAdmin = false;
    
    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n===== Food Delivery System =====");
            System.out.println("1. View Restaurant Menu");
            System.out.println("2. Place an Order");
            System.out.println("3. Cancel an Order");
            System.out.println("4. View Order Status");
            System.out.println("5. Admin Login");
            if (isAdmin) {
                System.out.println("6. Add Menu Item");
                System.out.println("7. Edit Menu Item");
                System.out.println("8. Remove Menu Item");
                System.out.println("9. View Total Sales");
            }
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    viewMenu();
                    break;
                case "2":
                    placeOrder();
                    break;
                case "3":
                    cancelOrder();
                    break;
                case "4":
                    viewOrderStatus();
                    break;
                case "5":
                    adminLogin();
                    break;
                case "6":
                    if (isAdmin) addMenuItem();
                    else System.out.println("Invalid option");
                    break;
                case "7":
                    if (isAdmin) editMenuItem();
                    else System.out.println("Invalid option");
                    break;
                case "8":
                    if (isAdmin) removeMenuItem();
                    else System.out.println("Invalid option");
                    break;
                case "9":
                    if (isAdmin) viewTotalSales();
                    else System.out.println("Invalid option");
                    break;
                case "0":
                    exit = true;
                    System.out.println("Thank you for using Food Delivery System!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void viewMenu() {
        List<MenuItems> menuItems = menuController.getAllMenuItems();
        System.out.println("\n===== Restaurant Menu =====");
        
        String currentCategory = "";
        for (MenuItems item : menuItems) {
            if (!item.getCategory().equals(currentCategory)) {
                currentCategory = item.getCategory();
                System.out.println("\n=== " + currentCategory + " ===");
            }
            System.out.printf("%s: %s - $%.2f\n", item.getItemId(), item.getName(), item.getPrice());
        }
    }
    
    private static void placeOrder() {
        // Start with a new order
        orderController.initializeCurrentOrder();
        Order currentOrder = orderController.getCurrentOrder();
        
        System.out.println("\n===== Place an Order =====");
        
        // Customer info
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your phone: ");
        String phone = scanner.nextLine();
        
        orderController.updateCustomerInfo(name, address, phone);
        
        boolean addingItems = true;
        while (addingItems) {
            viewMenu();
            System.out.print("\nEnter item ID to add (or 'done' to finish): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("done")) {
                addingItems = false;
            } else {
                orderController.addItemToCurrentOrder(input);
                System.out.printf("Current total: $%.2f\n", currentOrder.getTotalPrice());
            }
        }
        
        // Show order summary
        System.out.println("\n===== Order Summary =====");
        System.out.println("Customer: " + currentOrder.getCustomerName());
        System.out.println("Items:");
        for (MenuItems item : currentOrder.getOrderItems()) {
            System.out.printf("- %s: $%.2f\n", item.getName(), item.getPrice());
        }
        System.out.printf("Total: $%.2f\n", currentOrder.getTotalPrice());
        
        // Confirm order
        System.out.print("\nConfirm order? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            String orderId = orderController.submitOrder();
            if (orderId != null) {
                System.out.println("Order placed successfully! Order ID: " + orderId);
            } else {
                System.out.println("Failed to place order. Cart may be empty.");
            }
        } else {
            orderController.cancelCurrentOrder();
            System.out.println("Order cancelled.");
        }
    }
    
    private static void cancelOrder() {
        System.out.println("\n===== Cancel an Order =====");
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        
        Order order = orderController.getOrder(orderId);
        if (order != null) {
            orderController.updateOrderStatus(orderId, "Cancelled");
            System.out.println("Order cancelled successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }
    
    private static void viewOrderStatus() {
        System.out.println("\n===== View Order Status =====");
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        
        Order order = orderController.getOrder(orderId);
        if (order != null) {
            System.out.println("Order ID: " + orderId);
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("Customer: " + order.getCustomerName());
            System.out.printf("Total: $%.2f\n", order.getTotalPrice());
        } else {
            System.out.println("Order not found.");
        }
    }
    
    private static void adminLogin() {
        System.out.println("\n===== Admin Login =====");
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Simple password for demo purposes
        if (password.equals("admin123")) {
            isAdmin = true;
            System.out.println("Admin login successful.");
        } else {
            System.out.println("Invalid password.");
        }
    }
    
    private static void addMenuItem() {
        System.out.println("\n===== Add Menu Item =====");
        
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter item price: ");
        double price = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter item category: ");
        String category = scanner.nextLine();
        
        MenuItems newItem = new MenuItems(id, name, price, category);
        boolean added = menuController.addMenuItem(newItem);
        
        if (added) {
            System.out.println("Menu item added successfully.");
        } else {
            System.out.println("Failed to add menu item. ID might already exist.");
        }
    }
    
    private static void editMenuItem() {
        System.out.println("\n===== Edit Menu Item =====");
        
        System.out.print("Enter item ID to edit: ");
        String id = scanner.nextLine();
        
        MenuItems item = menuController.getMenuItemById(id);
        if (item != null) {
            System.out.print("Enter new name (current: " + item.getName() + "): ");
            String name = scanner.nextLine();
            if (name.isEmpty()) name = item.getName();
            
            System.out.print("Enter new price (current: " + item.getPrice() + "): ");
            String priceInput = scanner.nextLine();
            double price = priceInput.isEmpty() ? item.getPrice() : Double.parseDouble(priceInput);
            
            System.out.print("Enter new category (current: " + item.getCategory() + "): ");
            String category = scanner.nextLine();
            if (category.isEmpty()) category = item.getCategory();
            
            boolean updated = menuController.updateMenuItem(id, name, price, category);
            
            if (updated) {
                System.out.println("Menu item updated successfully.");
            } else {
                System.out.println("Failed to update menu item.");
            }
        } else {
            System.out.println("Menu item not found.");
        }
    }
    
    private static void removeMenuItem() {
        System.out.println("\n===== Remove Menu Item =====");
        
        System.out.print("Enter item ID to remove: ");
        String id = scanner.nextLine();
        
        boolean removed = menuController.deleteMenuItem(id);
        
        if (removed) {
            System.out.println("Menu item removed successfully.");
        } else {
            System.out.println("Failed to remove menu item. Item might not exist.");
        }
    }
    
    private static void viewTotalSales() {
        System.out.println("\n===== Total Sales =====");
        System.out.printf("Total sales for today: $%.2f\n", salesController.getTodaySales());
    }
}
