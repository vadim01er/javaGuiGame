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

    ControllerContinue(Stage stage){

    }

//    void contin() throws IOException {
//        Parent content = loader.load();
//        loader.getController();
//        Scene scene = new Scene(content);
//        stage.setScene(scene);
//    }

    @FXML
    private void clickContinue(ActionEvent actionEvent) throws InterruptedException {
        ViewGame game = new ViewGame(stage);
        game.setStage(stage);
        game.start();
    }
}
