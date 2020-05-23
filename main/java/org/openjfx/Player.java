package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Player extends Pane {

    private Rectangle player;
    private int R;
    static int score = 0;

    private Rectangle removeBonus = null;

    public Player(int R, Color clr) {
        this.R = R;
        player = new Rectangle(R, R, clr);
        getChildren().addAll(player);
    }

    public int getR() {
        return R;
    }

    enum Axis {X, Y}

    public void move(Axis axis, int speed) {
        Bonus.isBonus();
        for (Rectangle wall : App.walls) {
            if (wall.getBoundsInParent().intersects(this.getBoundsInParent())) {
                if (axis == Axis.X) {
                    if (speed > 0) {
                        if (this.getTranslateX() + R == wall.getTranslateX()) {
                            this.setTranslateX(this.getTranslateX() - speed);
                            return;
                        }
                    }
                    if (speed < 0) {
                        if (this.getTranslateX() == wall.getTranslateX() + wall.getWidth()) {
                            this.setTranslateX(this.getTranslateX() - speed);
                            return;
                        }
                    }
                }
                if (axis == Axis.Y) {
                    if (speed > 0) {
                        if (this.getTranslateY() + R == wall.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - speed);
                            return;
                        }
                    }
                    if (speed < 0) {
                        if (this.getTranslateY() == wall.getTranslateY() + wall.getHeight()) {
                            this.setTranslateY(this.getTranslateY() - speed);
                            return;
                        }
                    }
                }
            }
        }
        if (axis == Axis.X) {
            this.setTranslateX(this.getTranslateX() + speed);
        }
        if (axis == Axis.Y) {
            this.setTranslateY(this.getTranslateY() + speed);
        }
    }




}
