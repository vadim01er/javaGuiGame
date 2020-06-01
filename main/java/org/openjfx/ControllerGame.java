package org.openjfx;

import javafx.scene.Scene;

public class ControllerGame {

    private Scene scene;
    private ModelGame modelGame;

    ControllerGame(Scene scene, ModelGame modelGame){
        this.scene = scene;
        this.modelGame = modelGame;
    }

    void addListener(){
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case ESCAPE: ViewGame.startGame = !ViewGame.startGame; break;
                case SHIFT: ModelGame.speed = 2; break;
                case SPACE: modelGame.createBullet(); break;
                default: ModelGame.keys.put(keyEvent.getCode(), true); break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SHIFT: ModelGame.speed = 1; break;
                default: ModelGame.keys.put(keyEvent.getCode(), false); break;
            }
        });
//        scene.setOnMouseClicked(mouseEvent -> {});
    }
}
