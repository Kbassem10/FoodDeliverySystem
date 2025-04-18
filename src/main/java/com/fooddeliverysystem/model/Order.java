package com.fooddeliverysystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    // a list to save all user order
    private List<MenuItems> orderItems;

    //a variable to calculate totalPrice
    private double totalPrice;

    //some user data (optional)
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    
    // Add order status and timestamp
    private String orderStatus;
    private LocalDateTime orderTime;
    
    // Constructor
    public Order(String customerName, String customerAddress, String customerPhone) {
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.orderStatus = "Pending";
        this.orderTime = LocalDateTime.now();
    }
    
    // Default constructor
    public Order() {
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.customerName = "";
        this.customerAddress = "";
        this.customerPhone = "";
        this.orderStatus = "Pending";
        this.orderTime = LocalDateTime.now();
    }
    
    // Add item to order
    public void addItem(MenuItems item) {
        if (item != null) {
            orderItems.add(item);
            calculateTotal();
        }
    }
    
    // Remove item from order
    public boolean removeItem(MenuItems item) {
        boolean removed = orderItems.remove(item);
        if (removed) {
            calculateTotal();
        }
        return removed;
    }
    
    // Calculate total price
    private void calculateTotal() {
        this.totalPrice = 0.0;
        for (int i = 0; i < orderItems.size(); i++) {
            this.totalPrice += orderItems.get(i).getPrice();
        }
    }
    
    // Cancel order
    public boolean cancelOrder() {
        boolean removed = orderItems.removeAll(orderItems);
        calculateTotal();
        return removed;
    }
    
    public List<MenuItems> getOrderItems() {
        return orderItems;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerAddress() {
        return customerAddress;
    }
    
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}