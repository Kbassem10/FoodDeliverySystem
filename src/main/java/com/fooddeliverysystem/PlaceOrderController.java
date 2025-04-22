package com.fooddeliverysystem;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.model.MenuItems;

public class PlaceOrderController {

    @FXML
    private ComboBox<String> menuItemComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private TextField instructionsField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button back;

    private final MenuItemsController menuController = MenuItemsController.getInstance();
    private final OrderController orderController = OrderController.getInstance();

    @FXML
    public void initialize() {
        ObservableList<String> menuItems = FXCollections.observableArrayList();
        for (MenuItems item : menuController.getAllMenuItems()) {
            menuItems.add(item.getItemId() + " - " + item.getName());
        }
        menuItemComboBox.setItems(menuItems);

        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
    }

    @FXML
    private void handlePlaceOrder() {
        String selected = menuItemComboBox.getValue();
        Integer quantity = quantitySpinner.getValue();
        String instructions = instructionsField.getText();

        if (selected == null || quantity == null) {
            statusLabel.setText("Please select an item and quantity.");
            return;
        }

        String itemId = selected.split(" - ")[0];

        for (int i = 0; i < quantity; i++) {
            orderController.addItemToCurrentOrder(itemId);
        }

        String orderId = orderController.submitOrder();
        if (orderId != null) {
            statusLabel.setText("Order placed! Your Order ID: " + orderId);
        } else {
            statusLabel.setText("Failed to place order. Please try again.");
        }
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("CustomerMenu");
    }
}