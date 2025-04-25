package com.fooddeliverysystem;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.model.MenuItems;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class EditItemController {
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
    private Label imagePathLabel;
    @FXML
    private ImageView itemImageView;
    @FXML
    private Label statusLabel;

    private File selectedImageFile;
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

        itemImageView.setImage(null);
        imagePathLabel.setText("No image selected");
        selectedImageFile = null;
        statusLabel.setText("");
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Item Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        Window window = searchIdField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            selectedImageFile = file;
            imagePathLabel.setText(file.getName());
            itemImageView.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    private void handleSaveChanges() {
        if (loadedItem == null) {
            statusLabel.setText("Load an item first.");
            return;
        }
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String category = categoryField.getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || category.isEmpty()) {
            statusLabel.setText("Please fill all fields.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price.");
            return;
        }

        boolean updated = MenuItemsController.getInstance().updateMenuItem(
            loadedItem.getItemId(), name, price, category
        );
        if (updated) {
            statusLabel.setText("Item updated successfully!");
            loadedItem.setName(name);
            loadedItem.setPrice(price);
            loadedItem.setCategory(category);
        } else {
            statusLabel.setText("Failed to update item.");
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
        imagePathLabel.setText("No image selected");
        itemImageView.setImage(null);
        selectedImageFile = null;
    }
}