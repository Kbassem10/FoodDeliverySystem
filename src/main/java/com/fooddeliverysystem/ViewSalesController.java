package com.fooddeliverysystem;

import com.fooddeliverysystem.controller.SalesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Map;

public class ViewSalesController {
    @FXML
    private Label todaySalesLabel;
    @FXML
    private TableView<SalesRow> salesTable;
    @FXML
    private TableColumn<SalesRow, String> dateColumn;
    @FXML
    private TableColumn<SalesRow, String> amountColumn;

    @FXML
    public void initialize() {

        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().date));
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().amount));


        double todaySales = SalesController.getInstance().getTodaySales();
        todaySalesLabel.setText(String.format("%.2f EGP", todaySales));


        ObservableList<SalesRow> salesRows = FXCollections.observableArrayList();
        for (Map.Entry<LocalDate, Double> entry : SalesController.getInstance().getAllSalesData().entrySet()) {
            salesRows.add(new SalesRow(entry.getKey().toString(), String.format("%.2f", entry.getValue())));
        }
        salesTable.setItems(salesRows);
    }

    public static class SalesRow {
        public final String date;
        public final String amount;
        public SalesRow(String date, String amount) {
            this.date = date;
            this.amount = amount;
        }
    }

    @FXML
    private void handleBackToAdminMenu() {
        try {
            App.setRoot("AdminMenu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHomePage() {
        try {
            App.setRoot("HomePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}