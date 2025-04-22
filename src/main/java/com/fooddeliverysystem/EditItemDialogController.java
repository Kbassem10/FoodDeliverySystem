package com.fooddeliverysystem;

import java.io.IOException;

import com.fooddeliverysystem.model.MenuItems;

import com.fooddeliverysystem.controller.MenuItemsController;

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

    }

    @FXML
    private void FoundItem(MenuItems item) throws IOException {
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
        item.setName(ItemName.getText());
        item.setPrice(Double.parseDouble(ItemPrice.getText()));
        item.setCategory(ItemCategory.getText());
    }

    @FXML
    private void CheckId() throws IOException {
        MenuItems item = menuController.getMenuItemById(Idcheckerinput.getText());
        if (item != null) {
            FoundItem(item);
        } else {
            statusLabel.setText("Item Not Found!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
    @FXML
    private void switchtoSuccess() throws IOException {
        App.setRoot("Success");
    }

}
