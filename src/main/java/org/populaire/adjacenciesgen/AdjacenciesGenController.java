package org.populaire.adjacenciesgen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AdjacenciesGenController {
    @FXML
    private Label numberProvinces;
    @FXML
    private Label numberProvincesByAdjacenciesFind;
    private DataManager dataManager;
    private File bmpFile;
    private File csvFile;

    public AdjacenciesGenController() {
        this.dataManager = new DataManager();
    }

    @FXML
    protected void onUploadBitmapButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("Image Files"), "*.bmp"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.bmpFile = file;
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

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.csvFile = file;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No definitions file selected!");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onGenerateButtonClick() {
        if(this.bmpFile == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No image selected!");
        }
        try {
            if(this.csvFile == null) {
                this.dataManager.setProvincesCollectionByBitmap(this.bmpFile);
            } else {
                this.dataManager.setProvincesCollectionByDefinitionsCsv(this.csvFile);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while processing the image!");
        }

        this.numberProvinces.setText("Number of provinces : " + this.dataManager.getNumberProvinces());
    }
}