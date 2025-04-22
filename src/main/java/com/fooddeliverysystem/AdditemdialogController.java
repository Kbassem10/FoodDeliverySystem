package com.fooddeliverysystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.controller.MenuItemsController; // Correct import

public class AdditemdialogController {
    @FXML
    private TextField ItemID;
    @FXML
    private TextField ItemName;
    @FXML
    private TextField ItemCategory;
    @FXML
    private TextField ItemPrice;
    @FXML
    private Label statusLabel; // Add this to your FXML for feedback

    private MenuItemsController menuController = MenuItemsController.getInstance();

    @FXML
    private void switchToAdminMenu() throws IOException { 
        App.setRoot("AdminMenu"); 
    }
    @FXML
    private void switchtoSuccess() throws IOException {
        App.setRoot("Success");
    }

    @FXML
    private void addItem() {
        String id = ItemID.getText();
        String name = ItemName.getText();
        String category = ItemCategory.getText();
        double price;
        try {
            price = Double.parseDouble(ItemPrice.getText());
        } catch (NumberFormatException e) {
            if (statusLabel != null) {
                statusLabel.setText("Invalid price!");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
            return;
        }
        MenuItems newItem = new MenuItems(id, name, price, category);
        boolean added = menuController.addMenuItem(newItem);
        if (statusLabel != null) {
            if (added) {
                try {
                    System.out.println("Item added successfully: " + newItem);
                    switchtoSuccess();
                } catch (IOException e) {
                    statusLabel.setText("Error switching to success screen!");
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            } else {
                statusLabel.setText("Failed to add item (duplicate ID?)");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }
}
