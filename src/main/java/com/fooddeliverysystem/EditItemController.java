package com.fooddeliverysystem;

import java.io.File;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.model.MenuItems;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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

        String imagePath = loadedItem.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                File file = new File(imagePath);
                if (file.exists() && file.isFile()) {
                    itemImageView.setImage(new Image(file.toURI().toString()));
                    imagePathLabel.setText(file.getName());
                    selectedImageFile = file;
                } else {
                    imagePathLabel.setText("Image file not found");
                    System.err.println("Image file not found: " + imagePath);
                }
            } catch (Exception e) {
                imagePathLabel.setText("Error loading image");
                itemImageView.setImage(null);
                System.err.println("Error loading image: " + imagePath + " - " + e.getMessage());
                e.printStackTrace();
            }
        }

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
        String imagePath;
        if (selectedImageFile != null) {
            imagePath = selectedImageFile.getAbsolutePath();
        } else {
            imagePath = loadedItem.getImagePath();
        }

        if (name.isEmpty() || priceText.isEmpty() || category.isEmpty()) {
            statusLabel.setText("Please fill all fields.");
            return;
        }
        if (imagePath == null || imagePath.isEmpty()) {
            statusLabel.setText("Please ensure an image is associated with the item.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price < 0) throw new NumberFormatException("Price cannot be negative.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price: " + e.getMessage());
            return;
        }

        boolean updated = MenuItemsController.getInstance().updateMenuItem(
            loadedItem.getItemId(), name, price, category, imagePath
        );
        if (updated) {
            statusLabel.setText("Item updated successfully!");
            loadedItem.setName(name);
            loadedItem.setPrice(price);
            loadedItem.setCategory(category);
            loadedItem.setImagePath(imagePath);
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