package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminMenuController {

    @FXML
    private void switchToMainMenu() throws IOException { // Updated method name for clarity
        App.setRoot("MainMenu"); // Updated reference
    }

    @FXML
    private void addItem() {
        System.out.println("Add Item button clicked!");
        // Add logic to handle adding items
    }

    @FXML
    private void editItem() {
        System.out.println("Edit Item button clicked!");
        // Add logic to handle editing items
    }

    @FXML
    private void removeItem() {
        System.out.println("Remove Item button clicked!");
        // Add logic to handle removing items
    }

    @FXML
    private void viewTotalSales() {
        System.out.println("View Total Sales button clicked!");
        // Add logic to display total sales
    }
}