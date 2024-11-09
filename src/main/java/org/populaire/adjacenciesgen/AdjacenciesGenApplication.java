package org.populaire.adjacenciesgen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdjacenciesGenApplication extends Application {
    public static final String RESOURCE_PATH = "/org/populaire/adjacenciesgen/";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdjacenciesGenApplication.class.getResource(RESOURCE_PATH + "fxml/adjacenciesGen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(RESOURCE_PATH + "css/styles.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(RESOURCE_PATH + "images/logo.png")).toExternalForm()));
        stage.setTitle("AdjacenciesGen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}