package com.fooddeliverysystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.fooddeliverysystem.controller.SalesController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ViewTotalSalesController {

    @FXML
    private TableView<SalesEntry> salesTable;

    @FXML
    private TableColumn<SalesEntry, String> dateColumn;

    @FXML
    private TableColumn<SalesEntry, String> totalColumn;

    @FXML
    private Button back;

    @FXML
    private Label emptyLabel;

    private final SalesController salesController = SalesController.getInstance();

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        Map<LocalDate, Double> salesData = salesController.getAllSalesData();
        ObservableList<SalesEntry> salesEntries = FXCollections.observableArrayList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Map.Entry<LocalDate, Double> entry : salesData.entrySet()) {
            salesEntries.add(new SalesEntry(formatter.format(entry.getKey()), String.format("$%.2f", entry.getValue())));
        }

        salesTable.setItems(salesEntries);

        if (salesEntries.isEmpty()) {
            emptyLabel.setText("No sales data available.");
        } else {
            emptyLabel.setText("");
        }
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("AdminMenu");
    }

    public static class SalesEntry {
        private final String date;
        private final String total;

        public SalesEntry(String date, String total) {
            this.date = date;
            this.total = total;
        }

        public String getDate() {
            return date;
        }

        public String getTotal() {
            return total;
        }
    }
}