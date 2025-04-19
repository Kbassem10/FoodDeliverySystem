package com.fooddeliverysystem.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fooddeliverysystem.model.Order;

public class SalesController {
    
    private Map<LocalDate, Double> dailySales;
    private static SalesController instance;
    
    private SalesController() {
        dailySales = new HashMap<>();
    }
    
    public static SalesController getInstance() {
        if (instance == null) {
            instance = new SalesController();
        }
        return instance;
    }
    
    //Record a sale for the current day
    public void recordSale(Order order) {
        if (order == null) {
            return;
        }
        
        LocalDate today = LocalDate.now();
        double currentSales = dailySales.getOrDefault(today, 0.0);
        dailySales.put(today, currentSales + order.getTotalPrice());
    }
    
    //Get the total sales for today
    public double getTodaySales() {
        LocalDate today = LocalDate.now();
        return dailySales.getOrDefault(today, 0.0);
    }
    
    // Get the total sales for a specific date
    public double getSalesForDate(LocalDate date) {
        return dailySales.getOrDefault(date, 0.0);
    }
    
    //Get all daily sales data
    public Map<LocalDate, Double> getAllSalesData() {
        return new HashMap<>(dailySales);
    }
}
