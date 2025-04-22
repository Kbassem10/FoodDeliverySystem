package com.fooddeliverysystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.model.Order;

public class OrderStatusController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    private final OrderController orderController = OrderController.getInstance();

    @FXML
    private void handleCheckStatus() {
        String orderId = orderIdField.getText();
        if (orderId == null || orderId.isEmpty()) {
            statusLabel.setText("Please enter an order ID.");
            return;
        }
        Order order = orderController.getOrder(orderId);
        if (order != null) {
            statusLabel.setText("Status: " + order.getOrderStatus());
        } else {
            statusLabel.setText("Order not found.");
        }
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("CustomerMenu");
    }
}