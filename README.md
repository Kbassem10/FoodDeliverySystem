# Food Delivery System

A JavaFX application for managing food delivery orders.

## Overview

This Food Delivery System is a desktop application built with JavaFX that allows users to:

- Browse a menu of food items
- Add items to an order
- Submit orders
- Track order status
- Manage customer information

## Project Structure

```
food_delivery_system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── fooddeliverysystem/
│   │   │   │   │   ├── controller/       # Controllers for business logic
│   │   │   │   │   ├── model/            # Data models
│   │   │   │   │   ├── App.java          # Main application class
│   │   │   │   │   ├── PrimaryController.java
│   │   │   │   │   └── SecondaryController.java
│   │   │   └── module-info.java
│   │   └── resources/
│   │       └── com/
│   │           └── fooddeliverysystem/
│   │               ├── primary.fxml      # UI layout files
│   │               └── secondary.fxml
└── pom.xml                              # Maven configuration
```

## Requirements

- Java 11 or higher
- Maven 3.6 or higher

## How to Build

```bash
mvn clean package
```

## How to Run

```bash
mvn javafx:run
```

Or run the generated JAR file:

```bash
java -jar target/food_delivery_system-1.jar
```

## Features

### Menu Management
- View available menu items
- Add, update, or remove menu items (admin functionality)

### Order Management
- Create a new order
- Add/remove items from an order
- Update customer information
- Submit and track orders

## Design Pattern

The application follows the MVC (Model-View-Controller) architecture:
- **Models**: Classes in the `model` package (Order, MenuItems)
- **Views**: FXML files defining the UI layout
- **Controllers**: Classes in the `controller` package and the JavaFX controller classes

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

