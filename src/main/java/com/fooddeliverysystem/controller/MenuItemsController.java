// this is a file named controller that controls the backend and the frontend together
package com.fooddeliverysystem.controller;

import java.util.ArrayList;
import java.util.List;

import com.fooddeliverysystem.model.MenuItems;

public class MenuItemsController {

    //intialize a list to track the items of the menu
    private List<MenuItems> menuItemsList;

    //create an instance "Important to run in javaFX"
    private static MenuItemsController instance;
    
    //contructor for the class of the controller to ser a value for the menu
    private MenuItemsController() {
        menuItemsList = new ArrayList<>();
        initializeMenuItems();
    }
    
    //another constructor to getInstance it's important to run the javaFX
    public static MenuItemsController getInstance() {
        if (instance == null) {
            instance = new MenuItemsController();
        }
        return instance;
    }
    
    //initialize MenuItems by some value in the start of the app
    private void initializeMenuItems() {
        // Add some sample menu items
        addMenuItem(new MenuItems("P1", "Pizza Margherita", 109.99, "Pizza", System.getProperty("user.dir") + "/src/main/resources/com/fooddeliverysystem/images/PizzaMargerita.jpeg"));
        addMenuItem(new MenuItems("P2", "Pizza Pepperoni", 119.99, "Pizza", System.getProperty("user.dir") + "/src/main/resources/com/fooddeliverysystem/images/PizzaPepperoni.jpeg"));
        addMenuItem(new MenuItems("B1", "Cheeseburger", 179.99, "Burger", System.getProperty("user.dir") + "/src/main/resources/com/fooddeliverysystem/images/cheeseburger.jpeg"));
        addMenuItem(new MenuItems("D1", "V7", 12.99, "Drink", System.getProperty("user.dir") + "/src/main/resources/com/fooddeliverysystem/images/V7.jpeg"));
        addMenuItem(new MenuItems("D2", "Water", 4.99, "Drink", System.getProperty("user.dir") + "/src/main/resources/com/fooddeliverysystem/images/Water.jpeg"));
    }
    
    public List<MenuItems> getAllMenuItems() {
        return new ArrayList<>(menuItemsList);
    }

    public boolean addMenuItem(MenuItems menuItem) {
        if (menuItem != null && !menuItemExists(menuItem.getItemId())) {
            return menuItemsList.add(menuItem);
        }
        return false;
    }
    
    //Get menu item by ID
    public MenuItems getMenuItemById(String itemId) {
        return menuItemsList.stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .orElse(null);
    }
    
    //Update menu item - Added imagePath parameter
    public boolean updateMenuItem(String itemId, String name, double price, String category, String imagePath) {
        MenuItems item = getMenuItemById(itemId);
        if (item != null) {
            item.setName(name);
            try {
                item.setPrice(price);
            } catch (ArithmeticException e) {
                System.err.println("Error setting price for item " + itemId + ": " + e.getMessage());
                return false;
            }
            item.setCategory(category);
            item.setImagePath(imagePath);
            return true;
        }
        return false;
    }
    
    //Delete menu item
    public boolean deleteMenuItem(String itemId) {
        MenuItems item = getMenuItemById(itemId);
        if (item != null) {
            return menuItemsList.remove(item);
        }
        return false;
    }
    
    //Check if menu item exists
    public boolean menuItemExists(String itemId) {
        return menuItemsList.stream()
                .anyMatch(item -> item.getItemId().equals(itemId));
    }
}
