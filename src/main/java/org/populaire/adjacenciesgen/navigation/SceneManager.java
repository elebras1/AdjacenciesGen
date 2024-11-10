package org.populaire.adjacenciesgen.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.populaire.adjacenciesgen.AdjacenciesGenApplication;
import org.populaire.adjacenciesgen.controller.Controller;

import java.io.IOException;
import java.util.Objects;

import static org.populaire.adjacenciesgen.AdjacenciesGenApplication.RESOURCE_PATH;

public class SceneManager {

    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showAdjanciesGenScene() throws IOException {
        this.loadScene("adjacenciesGen.fxml");
    }

    public void showHelpScene() throws IOException {
        this.loadScene("help.fxml");
    }

    public void loadScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdjacenciesGenApplication.class.getResource(RESOURCE_PATH + "fxml/" + fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 480, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(RESOURCE_PATH + "css/styles.css")).toExternalForm());
        Controller controller = fxmlLoader.getController();
        controller.setSceneManager(this);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
