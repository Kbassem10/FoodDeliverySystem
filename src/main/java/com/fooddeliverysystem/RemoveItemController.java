package com.fooddeliverysystem;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.model.MenuItems;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RemoveItemController {
    @FXML
    private TextField searchIdField;
    @FXML
    private TextField itemIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField categoryField;
    @FXML
    private ImageView itemImageView;
    @FXML
    private Label statusLabel;

    private MenuItems loadedItem;

    @FXML
    private void handleLoadItem() {
        String id = searchIdField.getText().trim();
        loadedItem = MenuItemsController.getInstance().getMenuItemById(id);
        if (loadedItem == null) {
            statusLabel.setText("Item not found.");
            clearFields();
            return;
        }
        itemIdField.setText(loadedItem.getItemId());
        nameField.setText(loadedItem.getName());
        priceField.setText(String.valueOf(loadedItem.getPrice()));
        categoryField.setText(loadedItem.getCategory());

        // To make sure that it's hidden without messing with the .fxml files
        itemImageView.setImage(null);
        statusLabel.setText("");
    }

    @FXML
    private void handleRemoveItem() {
        if (loadedItem == null) {
            statusLabel.setText("Load an item first.");
            return;
        }
        boolean removed = MenuItemsController.getInstance().deleteMenuItem(loadedItem.getItemId());
        if (removed) {
            statusLabel.setText("Item removed successfully!");
            clearFields();
            loadedItem = null;
        } else {
            statusLabel.setText("Failed to remove item.");
        }
    }

    @FXML
    private void handleBackToAdminMenu() {
        try {
            App.setRoot("AdminMenu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHomePage() {
        try {
            App.setRoot("HomePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        itemIdField.clear();
        nameField.clear();
        priceField.clear();
        categoryField.clear();
        itemImageView.setImage(null);
    }
}