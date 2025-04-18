package model;

public class MenuItems {
    private String itemId;
    private String name;
    private double price;
    private String category;
    
    // Constructor
    public MenuItems(String itemId, String name, double price, String category) {
        if (price < 0){
            throw new ArithmeticException("Negative numbers are not allowed for a price");
        }
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    // Default constructor
    public MenuItems() {
        this.itemId = "";
        this.name = "";
        this.price = 0.0;
        this.category = "";
    }
    
    // Getters and setters
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
    
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}