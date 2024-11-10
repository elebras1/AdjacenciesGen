package org.populaire.adjacenciesgen.controller;

import javafx.fxml.FXML;
import org.populaire.adjacenciesgen.navigation.SceneManager;

public class HelpController implements Controller {

    private SceneManager sceneManager;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
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
