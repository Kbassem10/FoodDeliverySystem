package com.fooddeliverysystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fooddeliverysystem.controller.MenuItemsController;
import com.fooddeliverysystem.controller.OrderController;
import com.fooddeliverysystem.model.MenuItems;
import com.fooddeliverysystem.model.Order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PlaceOrderController {

    @FXML
    private ComboBox<String> menuItemComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private TextField instructionsField;

    @FXML
    private Label statusLabel;
    
    @FXML
    private Label totalPriceLabel;
    
    @FXML
    private TableView<CartItem> cartTableView;
    
    @FXML
    private TableColumn<CartItem, Integer> quantityColumn;
    
    @FXML
    private TableColumn<CartItem, String> itemNameColumn;
    
    @FXML
    private TableColumn<CartItem, Double> itemPriceColumn;
    
    @FXML
    private TableColumn<CartItem, Double> itemTotalColumn;
    
    @FXML
    private TableColumn<CartItem, Void> actionColumn;
    
    @FXML
    private TextField customerNameField;
    
    @FXML
    private TextField customerAddressField;
    
    @FXML
    private TextField customerPhoneField;
    
    @FXML
    private Button addItemButton;
    
    @FXML
    private Button placeOrderButton;

    @FXML
    private Button back;

    private final MenuItemsController menuController = MenuItemsController.getInstance();
    private final OrderController orderController = OrderController.getInstance();
    
    // Observable list to store cart items in the table
    private ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
    
    // Mapping between cart items and menu items for easier reference
    private List<String> itemIdsInCart = new ArrayList<>();

    @FXML
    public void initialize() {
                ObservableList<String> menuItems = FXCollections.observableArrayList();
        for (MenuItems item : menuController.getAllMenuItems()) {
            menuItems.add(item.getItemId() + " - " + item.getName());
        }
        menuItemComboBox.setItems(menuItems);
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        
        // Configure the table columns
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        // Configure the action column (remove button)
        setupActionColumn();
        
        // Set the cart items to the table
        cartTableView.setItems(cartItems);
        
        // Initialize order and update total
        updateTotalPrice();
    }
    
    private void setupActionColumn() {
        Callback<TableColumn<CartItem, Void>, TableCell<CartItem, Void>> cellFactory = 
            new Callback<TableColumn<CartItem, Void>, TableCell<CartItem, Void>>() {
                @Override
                public TableCell<CartItem, Void> call(final TableColumn<CartItem, Void> param) {
                    final TableCell<CartItem, Void> cell = new TableCell<CartItem, Void>() {
                        private final Button removeButton = new Button("Remove");
                        {
                            removeButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
                            removeButton.setOnAction(event -> {
                                CartItem cartItem = getTableView().getItems().get(getIndex());
                                handleRemoveItem(getIndex(), cartItem);
                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(removeButton);
                                setAlignment(Pos.CENTER);
                            }
                        }
                    };
                    return cell;
                }
            };
        actionColumn.setCellFactory(cellFactory);
    }
    
    @FXML
    private void handleAddToCart() {
        String selected = menuItemComboBox.getValue();
        Integer quantity = quantitySpinner.getValue();
        
        if (selected == null || quantity == null) {
            statusLabel.setText("Please select an item and quantity.");
            return;
        }
        
        String itemId = selected.split(" - ")[0];
        MenuItems item = menuController.getMenuItemById(itemId);
        
        if (item != null) {
            // Check if the same item already exists in cart
            int existingItemIndex = -1;
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getItemId().equals(itemId)) {
                    existingItemIndex = i;
                    break;
                }
            }
            
            if (existingItemIndex >= 0) {
                // Update existing item quantity
                CartItem existingItem = cartItems.get(existingItemIndex);
                int newQuantity = existingItem.getQuantity() + quantity;
                double newTotal = item.getPrice() * newQuantity;
                
                // Update in order controller - first remove old items
                for (int i = 0; i < existingItem.getQuantity(); i++) {
                    // Find and remove the item
                    Order currentOrder = orderController.getCurrentOrder();
                    List<MenuItems> orderItems = currentOrder.getOrderItems();
                    for (int j = 0; j < orderItems.size(); j++) {
                        if (orderItems.get(j).getItemId().equals(itemId)) {
                            currentOrder.removeItem(orderItems.get(j));
                            break;
                        }
                    }
                }
                
                // Then add new quantity
                for (int i = 0; i < newQuantity; i++) {
                    orderController.addItemToCurrentOrder(itemId);
                }
                
                // Update displayed cart item
                cartItems.set(existingItemIndex, new CartItem(
                    itemId, 
                    item.getName(), 
                    item.getPrice(), 
                    newQuantity,
                    newTotal
                ));
            } else {
                // Add new item
                for (int i = 0; i < quantity; i++) {
                    orderController.addItemToCurrentOrder(itemId);
                    itemIdsInCart.add(itemId);
                }
                
                // Add to cart display
                cartItems.add(new CartItem(
                    itemId,
                    item.getName(),
                    item.getPrice(),
                    quantity,
                    item.getPrice() * quantity
                ));
            }
            
            updateTotalPrice();
            statusLabel.setText("Added to cart!");
        }
    }
    
    private void handleRemoveItem(int index, CartItem cartItem) {
        if (index >= 0 && index < cartItems.size()) {
            String itemId = cartItem.getItemId();
            int quantity = cartItem.getQuantity();
            
            // Remove items from the order model
            Order currentOrder = orderController.getCurrentOrder();
            List<MenuItems> orderItems = new ArrayList<>(currentOrder.getOrderItems());
            
            int removedCount = 0;
            for (int i = 0; i < orderItems.size() && removedCount < quantity; i++) {
                MenuItems orderItem = orderItems.get(i);
                if (orderItem.getItemId().equals(itemId)) {
                    currentOrder.removeItem(orderItem);
                    removedCount++;
                    i--; // Adjust index as the list size is reduced
                }
            }
            
            // Remove from cart display
            cartItems.remove(index);
            
            updateTotalPrice();
            statusLabel.setText("Item removed from cart.");
        } else {
            statusLabel.setText("Failed to remove item.");
        }
    }
    
    @FXML
    private void handlePlaceOrder() {
        // Check if cart is empty
        if (cartItems.isEmpty()) {
            statusLabel.setText("Your cart is empty. Please add items before placing an order.");
            return;
        }
        
        // Get customer info
        String name = customerNameField.getText();
        String address = customerAddressField.getText();
        String phone = customerPhoneField.getText();
        
        // Validate customer info
        if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            statusLabel.setText("Please fill in all customer information fields.");
            return;
        }
        
        // Set customer info to the order
        orderController.updateCustomerInfo(name, address, phone);
        
        // Submit order
        String orderId = orderController.submitOrder();
        if (orderId != null) {
            statusLabel.setText("Order placed! Your Order ID: " + orderId);
            // Clear the cart
            cartItems.clear();
            itemIdsInCart.clear();
            updateTotalPrice();
            customerNameField.clear();
            customerAddressField.clear();
            customerPhoneField.clear();
        } else {
            statusLabel.setText("Failed to place order. Please try again.");
        }
    }
    
    private void updateTotalPrice() {
        double total = orderController.getCurrentOrderTotal();
        totalPriceLabel.setText(String.format("Total: $%.2f", total));
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("CustomerMenu");
    }
    
    // Inner class to represent cart items in the table
    public static class CartItem {
        private final String itemId;
        private final String name;
        private final double price;
        private final int quantity;
        private final double total;
        
        public CartItem(String itemId, String name, double price, int quantity, double total) {
            this.itemId = itemId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }
        
        public String getItemId() {
            return itemId;
        }
        
        public String getName() {
            return name;
        }
        
        public double getPrice() {
            return price;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public double getTotal() {
            return total;
        }
    }
}