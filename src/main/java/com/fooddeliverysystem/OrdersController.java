package com.fooddeliverysystem;

import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.controller.SalesController;
import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrdersController {
    @FXML
    private TextField orderIdField;
    @FXML
    private Label statusLabel;
    @FXML
    private ListView<String> itemsList;
    @FXML
    private Label infoLabel;

    private Order loadedOrder;

    @FXML
    private void handleLoadOrder() {
        String orderId = orderIdField.getText().trim();
        loadedOrder = OrderController.getInstance().getOrder(orderId);
        itemsList.getItems().clear();
        infoLabel.setText("");
        if (loadedOrder == null) {
            statusLabel.setText("N/A");
            infoLabel.setText("Order not found.");
            return;
        }
        statusLabel.setText(loadedOrder.getOrderStatus());
        for (MenuItems item : loadedOrder.getOrderItems()) {
            itemsList.getItems().add(item.getName() + " - " + String.format("%.2f EGP", item.getPrice()));
        }
    }

    @FXML
    private void handleCancelOrder() {
        if (loadedOrder == null) {
            infoLabel.setText("Load an order first.");
            return;
        }
        if ("Canceled".equalsIgnoreCase(loadedOrder.getOrderStatus())) {
            infoLabel.setText("Order already canceled.");
            return;
        }
        loadedOrder.setOrderStatus("Canceled");
        SalesController.getInstance().cancelSale(loadedOrder);
        statusLabel.setText("Canceled");
        infoLabel.setText("Order canceled and sales updated.");
    }

    @FXML
    private void handleBackToHome() {
        try {
            App.setRoot("HomePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}