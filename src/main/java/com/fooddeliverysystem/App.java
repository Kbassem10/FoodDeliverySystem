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
        stage.sizeToScene();
        stage.setX(0);
        stage.setY(0);
        stage.show();
        stage.setTitle("Food Delivery System");
    }

    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
        primaryStage.sizeToScene();
        primaryStage.setX(0);
        primaryStage.setY(0);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}