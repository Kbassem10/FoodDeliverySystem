module com.fooddeliverysystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.fooddeliverysystem to javafx.fxml;
    exports com.fooddeliverysystem;
    exports com.fooddeliverysystem.model;
}
