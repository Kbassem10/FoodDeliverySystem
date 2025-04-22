package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class SuccessController {

    @FXML
    private void goToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    private void goToAdminMenu() throws IOException {
        App.setRoot("AdminMainMenu");
    }

    @FXML
    private void goToCustomerMenu() throws IOException {
        // Implement this if you have a customer menu
        // App.setRoot("CustomerMenu");
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }
}