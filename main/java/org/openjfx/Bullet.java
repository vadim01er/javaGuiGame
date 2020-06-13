package org.openjfx;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class Bullet {

    private int speed = 3;
    private double x;
    private double y;
    private double height = 5;
    private double width = 10;
    private Color color = Color.RED;

    Bullet(double playerX, double playerY) {
        this.x = playerX;
        this.y = playerY;
    }

    void update(){
        this.x = this.x + speed;
    }

    void isHit(ArrayList<Wall> walls, ArrayList<Bullet> bullets) {
        for (Wall wall : walls) {
            if (this.x + this.width > wall.getX() && this.x < wall.getX() && this.y + this.height >= wall.getY() && this.y <= wall.getY() + wall.getHeight()) {
                bullets.remove(this);
                int health = wall.getHealth() - 1;
                wall.setHealth(health);
                if (health == 0) {
                    walls.remove(wall);
                }
                return;
            }
        }
    }

    void checkOutOfBounds(double rootX, double rootWidth, ArrayList<Bullet> bullets){
        if (this.x > rootWidth - rootX) {
            bullets.remove(this);
        }
    }

    double getHeight() {
        return height;
    }

    double getWidth() {
        return width;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    Color getColor(){
        return color;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    int getSpeed() {
        return speed;
    }
}
