package com.fooddeliverysystem;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminLoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("ADMIN".equals(username) && "1234".equals(password)) {
            App.setRoot("AdminMenu");
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
}