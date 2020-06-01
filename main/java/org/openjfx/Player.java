package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Player extends Pane {

    private int size;
    static int score = 0;

    private Rectangle player;


    Player(int size, Color clr) {
        this.size = size;
        player = new Rectangle(size, size, clr);
        getChildren().addAll(player);
    }

    public Rectangle getPlayer() {
        return player;
    }

    void moveX(int speed) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Pair wallAndNumber : ModelGame.walls) {
                Rectangle wall = (Rectangle) wallAndNumber.getKey();
                if (wall.getBoundsInParent().intersects(this.getBoundsInParent())) {
                    if (speed > 0) {
                        if (this.getTranslateX() + size == wall.getTranslateX()) {
                            this.setTranslateX(this.getTranslateX() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == wall.getTranslateX() + wall.getWidth()) {
                            this.setTranslateX(this.getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + speed / Math.abs(speed));
        }
    }

    void moveY(int speed) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Pair wallAndNumber : ModelGame.walls) {
                Rectangle wall = (Rectangle) wallAndNumber.getKey();
                if (wall.getBoundsInParent().intersects(this.getBoundsInParent())) {
                    if (speed > 0) {
                        if (this.getTranslateY() + size == wall.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            return;
                        }
                    } else {
                        if (this.getTranslateY() == wall.getTranslateY() + wall.getHeight()) {
                            this.setTranslateY(this.getTranslateY() + 1);
                            System.out.println("yes");
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + speed / Math.abs(speed));
        }
    }

    int getSize() {
        return size;
    }
}
