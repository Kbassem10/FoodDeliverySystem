package com.fooddeliverysystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;

/**
 * OrderController handles operations related to customer orders.
 * Similar to Django views, this controller processes requests related to orders
 * but doesn't render templates (JavaFX handles the view separately).
 */
public class OrderController {
    
    // In-memory storage of orders (similar to Django's ORM but in memory)
    private Map<String, Order> orders;
    
    // Reference to the MenuItemsController (like importing another view/service in Django)
    private MenuItemsController menuItemsController;
    
    // Add reference to SalesController
    private SalesController salesController;
    
    // Singleton instance
    private static OrderController instance;
    
    // Current active order (similar to session-based cart in Django)
    private Order currentOrder;
    
    /**
     * Private constructor
     * In Django, views are instantiated by the framework
     */
    private OrderController() {
        orders = new HashMap<>();
        menuItemsController = MenuItemsController.getInstance();
        salesController = SalesController.getInstance();
        initializeCurrentOrder();
    }
    
    /**
     * Get singleton instance
     * In Django, you wouldn't need this as views are instantiated by the framework
     */
    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    
    /**
     * Initialize a new current order
     * Similar to creating a new cart in Django session
     */
    public void initializeCurrentOrder() {
        currentOrder = new Order();
    }
    
    /**
     * Get the current active order
     * Similar to getting the cart from session in Django
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    /**
     * Add item to current order
     * Similar to adding an item to cart in Django
     */
    public void addItemToCurrentOrder(String menuItemId) {
        MenuItems item = menuItemsController.getMenuItemById(menuItemId);
        if (item != null) {
            currentOrder.addItem(item);
        }
    }
    
    /**
     * Remove item from current order
     * Similar to removing an item from cart in Django
     */
    public boolean removeItemFromCurrentOrder(String menuItemId) {
        MenuItems item = menuItemsController.getMenuItemById(menuItemId);
        if (item != null) {
            return currentOrder.removeItem(item);
        }
        return false;
    }
    
    /**
     * Submit the current order
     * Similar to checkout process in Django
     * return orderId of the submitted order
     */
    public String submitOrder() {
        if (currentOrder.getOrderItems().isEmpty()) {
            return null;
        }
        
        String orderId = generateOrderId();
        currentOrder.setOrderStatus("Confirmed");
        orders.put(orderId, currentOrder);
        
        // Record the sale
        salesController.recordSale(currentOrder);
        
        // Create a new current order (like clearing the cart after checkout)
        initializeCurrentOrder();
        
        return orderId;
    }
    
    /**
     * Generate a unique order ID
     * Similar to using UUID in Django models
     */
    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Get an order by its ID
     * Similar to Order.objects.get(id=orderId) in Django
     */
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
    
    /**
     * Get all orders
     * Similar to Order.objects.all() in Django
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    /**
     * Update customer information in the current order
     * Similar to updating form data in Django
     */
    public void updateCustomerInfo(String name, String address, String phone) {
        currentOrder.setCustomerName(name);
        currentOrder.setCustomerAddress(address);
        currentOrder.setCustomerPhone(phone);
    }
    
    /**
     * Cancel the current order
     * Similar to clearing the cart in Django
     */
    public void cancelCurrentOrder() {
        currentOrder.cancelOrder();
    }
    
    /**
     * Get the total price of the current order
     * Similar to calculating cart total in Django
     */
    public double getCurrentOrderTotal() {
        return currentOrder.getTotalPrice();
    }
    
    /**
     * Update order status
     */
    public boolean updateOrderStatus(String orderId, String status) {
        Order order = getOrder(orderId);
        if (order != null) {
            order.setOrderStatus(status);
            return true;
        }
        return false;
    }
    
    /**
     * Get the total sales for today
     */
    public double getTodayTotalSales() {
        return salesController.getTodaySales();
    }
    
}
