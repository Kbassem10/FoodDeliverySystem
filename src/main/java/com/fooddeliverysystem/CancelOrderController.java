package com.fooddeliverysystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.controller.SalesController;
import com.fooddeliverysystem.model.Order;

public class CancelOrderController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    private final OrderController orderController = OrderController.getInstance();
    private final SalesController salesController = SalesController.getInstance();

    @FXML
    private void handleCancelOrder() {
        String orderId = orderIdField.getText();
        if (orderId == null || orderId.isEmpty()) {
            statusLabel.setText("Please enter an order ID.");
            return;
        }
        Order order = orderController.getOrder(orderId);
        if (order != null && !"Cancelled".equals(order.getOrderStatus())) {
            boolean updated = orderController.updateOrderStatus(orderId, "Cancelled");
            if (updated) {
                salesController.cancelSale(order);
                statusLabel.setText("Order " + orderId + " cancelled.");
            } else {
                statusLabel.setText("Failed to cancel order.");
            }
        } else if (order != null) {
            statusLabel.setText("Order already cancelled.");
        } else {
            statusLabel.setText("Order not found.");
        }
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("CustomerMenu");
    }
}