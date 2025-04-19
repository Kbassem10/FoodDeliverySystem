package com.fooddeliverysystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    // a list to save all user order
    private List<MenuItems> orderItems;

    //a variable to calculate totalPrice
    private double totalPrice;

    //some user data for better UX
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    
    // Add order status and timestamp to calculate the sales per day and the current status
    private String orderStatus;
    private LocalDateTime orderTime;
    
    // parametrized constructor
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
    
    // Add item to order by getting and object and add to the Arraylist
    public void addItem(MenuItems item) {
        if (item != null) {
            orderItems.add(item);
            calculateTotal();
        }
    }
    
    // Remove item from order by getting the object
    public boolean removeItem(MenuItems item) {
        // a boolean variable that returns the status of this operation
        boolean removed = orderItems.remove(item);
        if (removed) {
            //if it's removed correctly if recalculate the total
            calculateTotal();
        }
        return removed;
    }
    
    // Calculate total price
    private void calculateTotal() {
        //resetting the total to 0
        this.totalPrice = 0.0;
        //loop over all of the ArrayList and add the prices to the total price variable
        for (int i = 0; i < orderItems.size(); i++) {
            this.totalPrice += orderItems.get(i).getPrice();
        }
    }
    
    // Cancel order
    public boolean cancelOrder() {
        //remove all items from the Arraylist if the order is canceled
        boolean removed = orderItems.removeAll(orderItems);

        //recalculate the total price means it will be 0.0
        calculateTotal();
        return removed;
    }
    
    public List<MenuItems> getOrderItems() {
        //return all of the Arraylist items
        return orderItems;
    }
    
    public double getTotalPrice() {
        //return the total price
        return totalPrice;
    }
    
    //setters and getters for user info
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
    
    //setters and getters for the status and the order time
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