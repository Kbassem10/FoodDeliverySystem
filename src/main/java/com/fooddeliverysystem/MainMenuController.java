package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToAdminMainMenu() throws IOException {
        App.setRoot("AdminMainMenu");
    }

    @FXML
    private void switchToCustomerMenu() throws IOException {
        App.setRoot("CustomerMenu");
    }

    @FXML
    private void handleCustomerLogin() throws IOException {
        App.setRoot("CustomerMenu");
    }

    @FXML
    private void handleAdminLogin() throws IOException {
        App.setRoot("AdminMainMenu");
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}