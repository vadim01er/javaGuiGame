package org.openjfx;

import javafx.scene.paint.Color;

class Wall extends CoordinateObject {

    private int health;

    Wall(double i, double j, int width, int height){
        super(i * width, j * height, width, height, Color.DARKGRAY);
        this.health = 1 + (int) Math.floor(Math.random() * 5);
    }

    Wall(double i, double j, int width, int height, Color color){
        super(i * width, j * height, width, height, color);
        this.health = -1;
    }

    int getHealth(){
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }
}
