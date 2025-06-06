package com.fooddeliverysystem;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; 
        Parent root = loadFXML("HomePage");
        scene = new Scene(root);
        stage.setScene(scene);
        // Set initial size
        stage.setWidth(1400);
        stage.setHeight(700);
        stage.show();
        stage.setTitle("Food Delivery System");
    }

    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}