package org.populaire.adjacenciesgen;

import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.populaire.adjacenciesgen.navigation.SceneManager;

import java.io.IOException;
import java.util.Objects;

public class AdjacenciesGenApplication extends Application {
    public static final String RESOURCE_PATH = "/org/populaire/adjacenciesgen/";
    private SceneManager pageController;
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(RESOURCE_PATH + "images/logo.png")).toExternalForm()));
        stage.setTitle("AdjacenciesGen");
        this.pageController = new SceneManager(stage);
        this.pageController.showAdjanciesGenScene();
    }

    public static void main(String[] args) {
        launch();
    }
}