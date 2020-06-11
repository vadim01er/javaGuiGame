package org.openjfx;

import javafx.scene.paint.Color;

class Player {

    private int score = 0;
    private int size;
    private double x;
    private double y;
    private Color color;

    Player(int size, double x, double y, Color color) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    void moveX(int speed) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Wall wall : ModelGame.walls) {
                if ((this.getY() + this.getSize() > wall.getY() && this.getY() < wall.getY() + wall.getWidth())) {
                    if (speed > 0) {
                        if (this.getX() + this.size >= wall.getX() & this.getX() < wall.getX()) {
                            this.setX(this.getX() - 1);
                        }
                    } else {
                        if (this.getX() <= wall.getX() + wall.getWidth() & this.getX() + this.getSize() > wall.getX() + wall.getWidth()) {
                            this.setX(this.getX() + 1);
                        }
                    }
                }
            }
            this.setX(this.getX() + speed / Math.abs(speed));
        }
    }

    void moveY(int speed) {
        for (int i = 0; i < Math.abs(speed); i++) {
            for (Wall wall : ModelGame.walls) {
                if (this.getX() + this.getSize() > wall.getX() && this.getX() < wall.getX() + wall.getWidth()) {
                    if (this.getY() + this.size >= wall.getY() & this.getY() < wall.getY()) {
                        this.setY(this.getY() - 1);
                    }
                    if (this.getY() <= wall.getY() + wall.getHeight() & this.getY() + this.getSize() > wall.getY() + wall.getHeight()) {
                        this.setY(this.getY() + 1);
                    }
                }
            }
            this.setY(this.getY() + speed / Math.abs(speed));
        }
    }

    void checkBonus(){
        for (int i = 0; i < ModelGame.bonuses.size(); i++) {
            Bonus bonus = ModelGame.bonuses.get(i);
            if (this.getX() < bonus.getX() + bonus.getWidth()
                    && this.getX() + this.getSize() >
                    bonus.getX()
                    && this.getY() < bonus.getY() + bonus.getHeight()
                    && this.getY() + this.getSize() > bonus.getY()){
                ModelGame.bonuses.remove(bonus);
                score++;
            }
        }
    }

    private void setY(double y) {
        this.y = y;
    }

    private void setX(double x) {
        this.x = x;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getSize() {
        return size;
    }

    Color getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }
}
