package com.fooddeliverysystem;

import java.io.IOException;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.model.MenuItems;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditItemDialogController {
    @FXML
    private TextField Idcheckerinput;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField ItemName;
    @FXML
    private TextField ItemPrice;
    @FXML
    private TextField ItemCategory;
    @FXML
    private Button confirmbutton;
    @FXML
    private Label NameLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label TextLabel;
    @FXML
    private Button saveButton;

    private final MenuItemsController menuController = MenuItemsController.getInstance();

    private MenuItems currentItem;

    @FXML
    private void switchToAdminMenu() throws IOException {
        App.setRoot("AdminMenu");
    }

    @FXML
    public void initialize() {
        ItemName.setVisible(false);
        ItemPrice.setVisible(false);
        ItemCategory.setVisible(false);
        NameLabel.setVisible(false);
        PriceLabel.setVisible(false);
        CategoryLabel.setVisible(false);
        TextLabel.setVisible(false);
        saveButton.setVisible(false);
        confirmbutton.visibleProperty().set(true);
        statusLabel.setText("");

    }

    @FXML
    private void FoundItem(MenuItems item) throws IOException {

        currentItem = item;

        Idcheckerinput.setVisible(false);
        confirmbutton.setVisible(false);

        ItemName.setVisible(true);
        ItemPrice.setVisible(true);
        ItemCategory.setVisible(true);
        
        NameLabel.setVisible(true);
        PriceLabel.setVisible(true);
        CategoryLabel.setVisible(true);
        TextLabel.setVisible(true);
        saveButton.setVisible(true);
        
        ItemName.setText(item.getName());
        ItemPrice.setText(String.valueOf(item.getPrice()));
        ItemCategory.setText(item.getCategory());
    }

    @FXML
    private void CheckId(){
        MenuItems item = menuController.getMenuItemById(Idcheckerinput.getText());
        if (item != null) {
            try {
                FoundItem(item);
            } catch (IOException e) {
                statusLabel.setText("Error loading item details!");
                statusLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Item Not Found!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void saveChanges() {
        if (currentItem == null) {
            statusLabel.setText("No item selected for editing!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        String name = ItemName.getText();
        String category = ItemCategory.getText();
        double price;
        
        // Validate inputs
        if (name.isEmpty() || category.isEmpty()) {
            statusLabel.setText("All fields are required!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        try {
            price = Double.parseDouble(ItemPrice.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price format!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        
        try { String originalName = currentItem.getName();
            double originalPrice = currentItem.getPrice();
            String originalCategory = currentItem.getCategory();
            
            // Update the model
            currentItem.setName(name);
            currentItem.setPrice(price);
            currentItem.setCategory(category);
            
            boolean updated = menuController.updateMenuItem(currentItem.getItemId(), name, price, category);
            
            if (updated) {
                System.out.println("Item updated successfully: " + currentItem);
                try {
                    switchtoSuccess();
                } catch (IOException e) {
                    statusLabel.setText("Error switching to success screen!");
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            } else {
                statusLabel.setText("Failed to update item!");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (ArithmeticException e) {
            statusLabel.setText(e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void switchtoSuccess() throws IOException {
        App.setRoot("Success");
    }

}
