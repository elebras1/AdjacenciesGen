package org.populaire.adjacenciesgen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AdjacenciesGenController {
    @FXML
    private Label welcomeText;
    private DataManager dataManager;

    public AdjacenciesGenController() {
        this.dataManager = new DataManager();
    }

    @FXML
    protected void onUploadImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("Image Files"), "*.bmp"));

        Stage stage = (Stage) this.welcomeText.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                this.dataManager.setProvincesCollection(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No image selected!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onUploadDefinitionsButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("Text Files"), "*.csv"));

        Stage stage = (Stage) this.welcomeText.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                this.dataManager.loadProvincesDefinitions(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No definitions file selected!");
            alert.showAndWait();
        }
    }
}