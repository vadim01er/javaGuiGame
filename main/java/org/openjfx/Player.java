package org.openjfx;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class Player extends CoordinateObject {

    private int score = 1;

    Player(double x, double y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    void moveX(int speed, double rootX, double rootWidth, ArrayList<Wall> walls) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Wall wall : walls) {
                if ((this.getY() + this.getHeight() > wall.getY() && this.getY() < wall.getY() + wall.getWidth())) {
                    if (speed > 0) {
                        if (this.getX() + this.getWidth() >= wall.getX() && this.getX() < wall.getX()) {
                            this.setX(this.getX() - 1);
                        }
                    } else {
                        if (this.getX() <= wall.getX() + wall.getWidth() && this.getX() + this.getWidth() > wall.getX() + wall.getWidth()) {
                            this.setX(this.getX() + 1);
                        }
                    }
                }
            }
            if (this.getX() + this.getWidth() > rootWidth - rootX) this.setX(this.getX() - 1);
            this.setX(this.getX() + speed / Math.abs(speed));
        }
    }

    void moveY(int speed, ArrayList<Wall> walls) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Wall wall : walls) {
                if (this.getX() + this.getWidth() > wall.getX() && this.getX() < wall.getX() + wall.getWidth()) {
                    if (this.getY() + this.getHeight() >= wall.getY() && this.getY() < wall.getY()) {
                        this.setY(this.getY() - 1);
                    }
                    if (this.getY() <= wall.getY() + wall.getHeight() && this.getY() + this.getHeight() > wall.getY() + wall.getHeight()) {
                        this.setY(this.getY() + 1);
                    }
                }
            }
            this.setY(this.getY() + speed / Math.abs(speed));
        }
    }

    boolean isBonus(Bonus bonus) {
        if (this.isCollision(bonus)) {
            score++;
            return true;
        }
        return false;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}
