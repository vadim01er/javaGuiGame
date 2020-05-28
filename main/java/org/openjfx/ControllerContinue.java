package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerContinue {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void contin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/continueGame.fxml"));
        Parent content = loader.load();
        loader.getController();
        Scene scene = new Scene(content);
        stage.setScene(scene);
    }

    @FXML
    private void clickContinue(ActionEvent actionEvent) throws InterruptedException {
        ControllerGame game = new ControllerGame();
        game.setStage(stage);
        game.start();
    }
}
