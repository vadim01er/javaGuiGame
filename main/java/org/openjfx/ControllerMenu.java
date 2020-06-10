package org.openjfx;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControllerMenu {

    private Stage stage;

    void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void clickStartButton() {
        ViewGame game = new ViewGame(stage);
        game.startNewGame();
    }
}
