package com.fooddeliverysystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;

// OrderController handles operations related to customer orders.
public class OrderController {
    
    // In-memory storage of orders (similar to Django's ORM but in memory)
    private Map<String, Order> orders;

    // Reference to the MenuItemsController (like importing another view/service in Django)
    private MenuItemsController menuItemsController;
    
    // Add reference to SalesController
    private SalesController salesController;
    
    // Singleton instance
    private static OrderController instance;
    private static int nextOrderId = 1; // Static counter for order IDs
    
    // Current active order (similar to session-based cart in Django)
    private Order currentOrder;
    
    //Private constructor
    private OrderController() {
        orders = new HashMap<>();
        menuItemsController = MenuItemsController.getInstance();
        salesController = SalesController.getInstance();
        initializeCurrentOrder();
    }
    
    //Get singleton instance
    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    
    //Initialize a new current order
    public void initializeCurrentOrder() {
        currentOrder = new Order();
    }
    
    //Get the current active order
    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    //Add item to current order
    public void addItemToCurrentOrder(String menuItemId) {
        MenuItems item = menuItemsController.getMenuItemById(menuItemId);
        if (item != null) {
            currentOrder.addItem(item);
        }
    }
    
    //Remove item from current order
    public boolean removeItemFromCurrentOrder(String menuItemId) {
        MenuItems item = menuItemsController.getMenuItemById(menuItemId);
        if (item != null) {
            return currentOrder.removeItem(item);
        }
        return false;
    }
    
    //Submit the current order
    public String submitOrder() {
        if (currentOrder == null || currentOrder.getOrderItems().isEmpty()) {
            return null;
        }
        
        String orderId = String.valueOf(nextOrderId++); // Assign and increment
        currentOrder.setOrderId(orderId);
        currentOrder.setOrderStatus("Confirmed");

        //in the hashmap
        orders.put(orderId, currentOrder);
        //{7, Order_object}

        // Record the sale
        salesController.recordSale(currentOrder);
        
        // Create a new current order (like clearing the cart after checkout)
        initializeCurrentOrder();
        
        return orderId;
    }
    
    
    //Get an order by its ID
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
    
    //Get all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    //Update customer information in the current order
    public void updateCustomerInfo(String name, String address, String phone) {
        currentOrder.setCustomerName(name);
        currentOrder.setCustomerAddress(address);
        currentOrder.setCustomerPhone(phone);
    }
    
    //Cancel the current order
    public void cancelCurrentOrder() {
        currentOrder.cancelOrder();
    }
    
    //Get the total price of the current order
    public double getCurrentOrderTotal() {
        return currentOrder.getTotalPrice();
    }
    
    //Update order status
    // public boolean updateOrderStatus(String orderId, String status) {
    //     Order order = getOrder(orderId);
    //     if (order != null) {
    //         order.setOrderStatus(status);
    //         return true;
    //     }
    //     return false;
    // }
    
    //Get the total sales for today
    public double getTodayTotalSales() {
        return salesController.getTodaySales();
    }
    
}
