package org.openjfx;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControllerMenu {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void clickStartButton() {
        ControllerGame game = new ControllerGame();
        game.setStage(stage);
        game.startNewGame();
    }
}
