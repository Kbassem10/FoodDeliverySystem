package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class CustomerMenuController {

    @FXML
    private void switchToViewMenu() throws IOException {
        App.setRoot("ViewMenu");
    }

    @FXML
    private void switchToPlaceOrder() throws IOException {
        App.setRoot("PlaceOrder");
    }

    @FXML
    private void switchToCancelOrder() throws IOException {
        App.setRoot("CancelOrder");
    }

    @FXML
    private void switchToViewOrderStatus() throws IOException {
        App.setRoot("OrderStatus");
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
}