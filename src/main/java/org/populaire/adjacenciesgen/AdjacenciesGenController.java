package org.populaire.adjacenciesgen;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

public class AdjacenciesGenController {
    @FXML
    private Label numberProvinces;
    private DataManager dataManager;
    private File bmpFile;
    private File csvFile;

    public AdjacenciesGenController() {
        this.dataManager = new DataManager();
        this.bmpFile = null;
        this.csvFile = null;

    }

    @FXML
    protected void onUploadBitmapButtonClick() {
        this.dataManager.clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("Image Files"), "*.bmp"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            this.bmpFile = file;
        } else {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No image selected!");
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
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No file selected!");
        }
    }

    @FXML
    protected void onGenerateButtonClick() {
        if(this.bmpFile == null) {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No image selected!");
            return;
        }
        this.dataManager.readBitmapFile(this.bmpFile);

        if(this.csvFile == null) {
            this.dataManager.setProvincesCollectionByBitmap();
        } else {
            this.dataManager.setProvincesCollectionByDefinitionsCsv(this.csvFile);
        }

        this.numberProvinces.setText("Number of provinces : " + this.dataManager.getNumberProvinces());
        this.dataManager.setAdjacenciesCollection();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(null);
        if(directory != null) {
            File file = new File(directory, "adjacencies.json");
            this.dataManager.writeAdjacenciesJson(file);
        } else {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No directory selected!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/alert.css")).toExternalForm());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}