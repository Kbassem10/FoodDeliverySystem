package com.fooddeliverysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;

import java.io.IOException;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.model.MenuItems;

public class ViewMenuController {

    public static final ObservableList<MenuItems> menuData = FXCollections.observableArrayList();

    @FXML
    private TableView<MenuItems> menuTable;

    @FXML
    private TableColumn<MenuItems, String> nameColumn;

    @FXML
    private TableColumn<MenuItems, Double> priceColumn;

    @FXML
    private TableColumn<MenuItems, String> descColumn;

    @FXML
    private Button back;

    private final MenuItemsController menuController = MenuItemsController.getInstance();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        menuTable.setItems(menuData);
        menuData.setAll(menuController.getAllMenuItems());
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("CustomerMenu");
    }
}
