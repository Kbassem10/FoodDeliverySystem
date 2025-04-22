module com.fooddeliverysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.fooddeliverysystem to javafx.fxml;
    opens com.fooddeliverysystem.model to javafx.base;
    exports com.fooddeliverysystem;
}
