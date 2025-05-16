package com.fooddeliverysystem.model;

public class MenuItems {
    private String itemId;
    private String name;
    private double price;
    private String category;
    private String imagePath;

    // parametrized Constructor
    public MenuItems(String itemId, String name, double price, String category, String imagePath) {
        if (price < 0){
            throw new ArithmeticException("Negative numbers are not allowed for a price");
        }
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imagePath = imagePath;
    }

    // Old constructor for backward compatibility
    //without the image
    public MenuItems(String itemId, String name, double price, String category) {
        this(itemId, name, price, category, null);
    }

    // Default constructor
    public MenuItems() {
        this("", "", 0.0, "", null);
    }

    // setters and getters
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price < 0){
            throw new ArithmeticException("Negative numbers are not allowed for a price");
        }
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}