package org.populaire.adjacenciesgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.populaire.adjacenciesgen.navigation.SceneManager;
import org.populaire.adjacenciesgen.service.DataManager;

import java.io.File;
import java.util.Objects;

import static org.populaire.adjacenciesgen.AdjacenciesGenApplication.RESOURCE_PATH;

public class AdjacenciesGenController implements Controller {
    @FXML
    private Label numberProvinces;
    @FXML
    private Label successAdjancencies;
    @FXML
    private ImageView imageViewHelp;
    @FXML
    private ImageView imageViewEditBitmap;
    private DataManager dataManager;
    private File bmpFile;
    private File csvFile;
    private SceneManager sceneManager;

    public AdjacenciesGenController() {
        this.dataManager = new DataManager();
        this.bmpFile = null;
        this.csvFile = null;
    }

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    protected void initialize() {
        Image imageHelp = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RESOURCE_PATH + "images/help.png")));
        this.imageViewHelp.setImage(imageHelp);
        this.imageViewHelp.setFitWidth(20);
        this.imageViewHelp.setFitHeight(20);

        Image imageEditBitmap = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RESOURCE_PATH + "images/edit.png")));
        this.imageViewEditBitmap.setImage(imageEditBitmap);
        this.imageViewEditBitmap.setFitWidth(20);
        this.imageViewEditBitmap.setFitHeight(20);
    }

    @FXML
    protected void onHelpButtonClick() {
        try {
            this.sceneManager.showHelpScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onUploadBitmapButtonClick() {
        this.clear();
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
            this.showAlert(Alert.AlertType.WARNING, "Warning", "The bitmap file containing the provinces is mandatory!");
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
            File adjacenciesFile = new File(directory, "adjacencies.json");
            this.dataManager.writeAdjacenciesJson(adjacenciesFile);
            if(this.csvFile == null) {
                File definitionsFile = new File(directory, "definitions.csv");
                this.dataManager.writeDefinitionsCsv(definitionsFile);
                this.successAdjancencies.setText("Adjacencies and Definitions file generated successfully!");
            } else {
                this.successAdjancencies.setText("Adjacencies file generated successfully!");
            }
        } else {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No directory selected!");
        }
    }

    @FXML
    protected void onEditBitmapButtonClick() {
        if(this.bmpFile != null) {
            try {
                this.sceneManager.showBitmapEditorScene(this.bmpFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.showAlert(Alert.AlertType.WARNING, "Warning", "No image selected!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource(RESOURCE_PATH + "css/alert.css")).toExternalForm());
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clear() {
        this.dataManager.clear();
        this.bmpFile = null;
        this.csvFile = null;
        this.numberProvinces.setText("");
    }
}