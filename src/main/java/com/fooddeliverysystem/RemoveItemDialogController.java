package com.fooddeliverysystem;

import java.io.IOException;

import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.controller.MenuItemsController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoveItemDialogController {
    @FXML
    private Label statusLabel;
    @FXML
    private TextField Idcheckerinput;

    private MenuItemsController menuController = MenuItemsController.getInstance();

    @FXML
    private void switchToMainMenu() throws IOException { 
        App.setRoot("AdminMenu"); 
    }
    @FXML
    private void switchtoSuccess() throws IOException {
        App.setRoot("Success");
    }

    @FXML
    private void CheckId() throws IOException {
        String itemId = Idcheckerinput.getText();
        MenuItems item = menuController.getMenuItemById(itemId);
        if (item != null) {
            boolean removed = menuController.deleteMenuItem(itemId);
            if (removed) {
                switchtoSuccess();
            } else {
                statusLabel.setText("Failed to remove item.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            statusLabel.setText("Item Not Found!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
