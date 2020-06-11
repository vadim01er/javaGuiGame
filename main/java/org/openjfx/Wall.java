package org.openjfx;

import javafx.scene.paint.Color;

class Wall {

    private double height = 40;
    private double width = 40;
    private Color color = Color.DARKGRAY;
    private int health;
    private double x;
    private double y;

    Wall(double i, double j){
        this.health = 1 + (int) Math.floor(Math.random() * 4);
        this.x= i * width;
        this.y = j * height;
    }

    Wall(double i, double j, Color color){
        this.health = 1 + (int) Math.floor(Math.random() * 4);
        this.x= i * width;
        this.y = j * height;
        this.color = color;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    Color getColor() {
        return color;
    }

    double getHeight() {
        return height;
    }

    double getWidth() {
        return width;
    }

    int getHealth(){
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }
}
