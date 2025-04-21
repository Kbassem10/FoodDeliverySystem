package com.fooddeliverysystem;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToAdminMainMenu() throws IOException {
        App.setRoot("AdminMainMenu"); // Updated reference
    }
}