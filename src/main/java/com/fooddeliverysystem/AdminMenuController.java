package com.fooddeliverysystem;

import java.io.IOException;

import javafx.fxml.FXML;

public class AdminMenuController {

    @FXML
    private void switchtoAddItem() throws IOException {
        App.setRoot("AddItem");
    }

    @FXML
    private void switchtoEditItem() throws IOException {
        App.setRoot("EditItem");
    }

    @FXML
    private void switchtoRemoveItem() throws IOException {
        App.setRoot("RemoveItem");
    }

    @FXML
    private void switchtoViewSales() throws IOException {
        App.setRoot("ViewSales");
    }
    @FXML
    private void switchtoHome() throws IOException {
        App.setRoot("HomePage");
    }
}