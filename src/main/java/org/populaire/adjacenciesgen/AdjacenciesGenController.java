package org.populaire.adjacenciesgen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdjacenciesGenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Adjacencies file generator!");
    }
}