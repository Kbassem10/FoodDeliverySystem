module com.fooddeliverysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;


    opens com.fooddeliverysystem to javafx.fxml;
    exports com.fooddeliverysystem;
}
