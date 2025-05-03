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

public class AddItemController {
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

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Item Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        Window window = itemIdField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            selectedImageFile = file;
            imagePathLabel.setText(file.getName());
            itemImageView.setImage(new Image(file.toURI().toString()));
            System.out.println("Selected image: " + file.getAbsolutePath());
        }
    }

    @FXML
    private void handleAddItem() {
        String itemId = itemIdField.getText().trim();
        String name = nameField.getText().trim();
        String priceText = priceField.getText().trim();
        String category = categoryField.getText().trim();

        if (itemId.isEmpty() || name.isEmpty() || priceText.isEmpty() || category.isEmpty() || selectedImageFile == null) {
            statusLabel.setText("Please fill all fields and select an image.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price.");
            return;
        }

        if (price < 0){
            statusLabel.setText("Invalid Negative Number");
            return;
        }

        // Use the image path!
        MenuItems newItem = new MenuItems(itemId, name, price, category, selectedImageFile.getAbsolutePath());

        boolean added = MenuItemsController.getInstance().addMenuItem(newItem);
        if (added) {
            statusLabel.setText("Item added successfully!");
            itemIdField.clear();
            nameField.clear();
            priceField.clear();
            categoryField.clear();
            imagePathLabel.setText("No image selected");
            itemImageView.setImage(null);
            selectedImageFile = null;
        } else {
            statusLabel.setText("Item ID already exists.");
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
}