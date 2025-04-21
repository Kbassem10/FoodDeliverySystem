package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class AdminMainMenuController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu"); // Updated reference
    }

    @FXML
    private void checkPassword() throws IOException {
        String enteredPassword = passwordField.getText();

        if ("1234".equals(enteredPassword)) {
            statusLabel.setText("Access Granted!");
            statusLabel.setStyle("-fx-text-fill: green;");
            App.setRoot("AdminMenu"); // Ensure "AdminMenu.fxml" exists in the correct location
        } else {
            statusLabel.setText("Invalid Password!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}