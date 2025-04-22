package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminMenuController {

    @FXML
    private void switchToMainMenu() throws IOException { 
        App.setRoot("MainMenu"); 
    }
    @FXML
    private void switchToAddItemDialog() throws IOException { 
        App.setRoot("Additemdialog"); 
    }
    
    @FXML
    private void switchToEditItemDialog() throws IOException { 
        App.setRoot("EditItemDialog"); 
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