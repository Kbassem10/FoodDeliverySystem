package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToAdminMainMenu() throws IOException {
        App.setRoot("AdminMainMenu"); // Updated reference
    }

    @FXML
    private void switchToCustomerMenu() throws IOException {
        // TODO: Implement customer menu navigation
        // App.setRoot("CustomerMenu");
    }
}