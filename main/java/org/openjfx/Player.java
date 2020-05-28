package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Player extends Pane {

    private int size;
    static int score = 0;


    Player(int size, Color clr) {
        this.size = size;
        Rectangle player = new Rectangle(size, size, clr);
        getChildren().addAll(player);
    }

    void moveX(int speed) {
//        Bonus.isBonus();
        for (Pair wallAndNumber : ControllerGame.walls) {
            Rectangle wall = (Rectangle) wallAndNumber.getKey();
            if (wall.getBoundsInParent().intersects(this.getBoundsInParent())) {
                if (speed > 0) {
                    if (this.getTranslateX() + size == wall.getTranslateX()) {
                        this.setTranslateX(this.getTranslateX() - speed);
                        return;
                    }
                } else {
                    if (this.getTranslateX() == wall.getTranslateX() + wall.getWidth()) {
                        this.setTranslateX(this.getTranslateX() - speed);
                        return;
                    }
                }
            }
        }
        this.setTranslateX(this.getTranslateX() + speed);
    }

    void moveY(int speed) {
//        Bonus.isBonus();
        for (Pair wallAndNumber : ControllerGame.walls) {
            Rectangle wall = (Rectangle) wallAndNumber.getKey();
            if (wall.getBoundsInParent().intersects(this.getBoundsInParent())) {
                if (speed > 0) {
                    if (this.getTranslateY() + size == wall.getTranslateY()) {
                        this.setTranslateY(this.getTranslateY() - speed);
                        return;
                    }
                } else {
                    if (this.getTranslateY() == wall.getTranslateY() + wall.getHeight()) {
                        this.setTranslateY(this.getTranslateY() - speed);
                        return;
                    }
                }
            }
        }
        this.setTranslateY(this.getTranslateY() + speed);
    }

    int getSize() {
        return size;
    }

}
