package com.fooddeliverysystem;

import javafx.fxml.FXML;

public class HomePageController {
    @FXML
    private void switchtoHome() throws Exception {
        App.setRoot("HomePage");
    }
    @FXML
    private void switchtoMenu() throws Exception {
        App.setRoot("Menu");
    }
    @FXML
    private void switchtoOrders() throws Exception {
        App.setRoot("Orders");
    }
    @FXML
    private void switchtoAdminLogin() throws Exception {
        App.setRoot("AdminLogin");
    }
}
