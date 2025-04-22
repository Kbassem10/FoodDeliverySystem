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
    private void switchToRemoveItemDialog() throws IOException {
        App.setRoot("RemoveItemDialog"); 
    }

    @FXML
    private void viewTotalSales() {
        System.out.println("View Total Sales button clicked!");
        // Add logic to display total sales
    }
}