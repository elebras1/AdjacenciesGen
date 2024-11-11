package org.populaire.adjacenciesgen.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.populaire.adjacenciesgen.navigation.SceneManager;

import java.util.Objects;

import static org.populaire.adjacenciesgen.AdjacenciesGenApplication.RESOURCE_PATH;

public class HelpController implements Controller {

    @FXML
    private ImageView imageViewBmp;
    @FXML
    private ImageView imageViewDefinitions;
    @FXML
    private ImageView imageViewAdjacencies;
    private SceneManager sceneManager;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    protected void initialize() {
        Image imageProvinces = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RESOURCE_PATH + "images/provinces.png")));
        this.imageViewBmp.setImage(imageProvinces);
        this.imageViewBmp.setFitWidth(300);
        Image imageDefinitions = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RESOURCE_PATH + "images/definitions.png")));
        this.imageViewDefinitions.setImage(imageDefinitions);
        this.imageViewDefinitions.setFitWidth(300);
        Image imageAdjacencies = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RESOURCE_PATH + "images/adjacencies.png")));
        this.imageViewAdjacencies.setImage(imageAdjacencies);
        this.imageViewAdjacencies.setFitWidth(300);
    }

    @FXML
    protected void onReturnButtonClick() {
        try {
            this.sceneManager.showAdjanciesGenScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
