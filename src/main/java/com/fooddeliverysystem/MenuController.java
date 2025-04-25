package com.fooddeliverysystem;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MenuController {
    @FXML
    private TilePane menuGrid;
    @FXML
    private ListView<String> cartList;
    @FXML
    private Label cartTotal;
    @FXML
    private Button checkoutButton;
    @FXML
    private VBox menuVBox;
    @FXML
    private VBox cartVBox;

    private final ObservableList<String> cartItems = FXCollections.observableArrayList();
    private final OrderController orderController = OrderController.getInstance();

    @FXML
    public void initialize() {

        menuVBox.prefHeightProperty().bind(cartVBox.heightProperty());

        menuGrid.setOrientation(javafx.geometry.Orientation.VERTICAL);
        menuGrid.setPrefRows(3);
        menuGrid.setHgap(8);
        menuGrid.setVgap(16);

        MenuItemsController menuController = MenuItemsController.getInstance();
        for (MenuItems item : menuController.getAllMenuItems()) {
            menuGrid.getChildren().add(createMenuItemCard(item));
        }
        cartList.setItems(cartItems);
        updateCartDisplay();

        checkoutButton.setOnAction(e -> handleCheckout());
    }

    private VBox createMenuItemCard(MenuItems item) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: #fff; -fx-border-color: #222; -fx-padding: 10; -fx-alignment: center;");
        ImageView imageView;
        if (item.getImagePath() != null && !item.getImagePath().isEmpty()) {
            try {
                imageView = new ImageView(new Image("file:" + item.getImagePath()));
            } catch (Exception e) {
                imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/fooddeliverysystem/Images/Logo.png")));
            }
        } else {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/fooddeliverysystem/Images/Logo.png")));
        }
        imageView.setFitWidth(120);
        imageView.setFitHeight(100);

        Label name = new Label(item.getName());
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label price = new Label(String.format("%.2f EGP", item.getPrice()));
        price.setStyle("-fx-text-fill: #da7c10; -fx-font-size: 16px;");
        Button addButton = new Button("+");
        addButton.setStyle("-fx-background-color: #da7c10; -fx-text-fill: white; -fx-font-size: 18px;");

        addButton.setOnAction(e -> {
            orderController.addItemToCurrentOrder(item.getItemId());
            updateCartDisplay();
        });

        card.getChildren().addAll(imageView, name, price, addButton);
        return card;
    }

    private void updateCartDisplay() {
        Order currentOrder = orderController.getCurrentOrder();
        cartItems.clear();
        for (MenuItems item : currentOrder.getOrderItems()) {
            cartItems.add(item.getName() + " - " + String.format("%.2f EGP", item.getPrice()));
        }
        cartTotal.setText("Total: " + String.format("%.2f", currentOrder.getTotalPrice()));
    }

    private void handleCheckout() {
        if (orderController.getCurrentOrder().getOrderItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cart is empty", "Please add items to the cart before checking out.");
            return;
        }
        orderController.updateCustomerInfo("Guest", "N/A", "N/A");
        String orderId = orderController.submitOrder();
        if (orderId != null) {
            showAlert(Alert.AlertType.INFORMATION, "Order Confirmed", "Your order has been placed! Order ID: " + orderId);
            updateCartDisplay();
        } else {
            showAlert(Alert.AlertType.ERROR, "Order Failed", "Could not place the order.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void switchToHome() throws Exception {

        orderController.getCurrentOrder().getOrderItems().clear();
        updateCartDisplay();
        App.setRoot("HomePage");
    }
}
