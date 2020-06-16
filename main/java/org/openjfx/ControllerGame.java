package org.openjfx;

import javafx.scene.Scene;

public class ControllerGame {

    private Scene scene;
    private ModelGame modelGame;
    private ViewGame viewGame;

    ControllerGame(Scene scene, ModelGame modelGame){
        this.scene = scene;
        this.modelGame = modelGame;
    }

    void addListener(){
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case ESCAPE: modelGame.startGame= !modelGame.startGame; break;
                case SHIFT: modelGame.speed = 2; break;
//                case SPACE: modelGame.createBullet(); break;
                default: modelGame.keys.put(keyEvent.getCode(), true); break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SHIFT: modelGame.speed = 1; break;
                default: modelGame.keys.put(keyEvent.getCode(), false); break;
            }
        });
//        scene.setOnMouseClicked(mouseEvent -> {});
    }
}
