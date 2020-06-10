package org.openjfx;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class Wall {

    private static double height = 40;
    private static double width = 40;
    private Color color = Color.DARKGRAY;
    private int health;
    private double x;
    private double y;

    private Wall(double x, double y){
        this.health = 1 + (int) Math.floor(Math.random() * 4);
        this.x= x;
        this.y = y;
    }

    private Wall(double x, double y, Color color){
        this.health = 1 + (int) Math.floor(Math.random() * 4);
        this.x= x;
        this.y = y;
        this.color = color;
    }

    static void createWall(int levelNumber){
        ArrayList<String> nowLevel = Level.levels.get(levelNumber);
        for (int i = 0; i < nowLevel.size(); i++) {
            for (int j = 0; j < nowLevel.get(0).length(); j++) {
                if (nowLevel.get(i).charAt(j) == '1') {
                    Wall wall = new Wall(i * width,j * height);
                    ModelGame.walls.add(wall);
                }
            }
        }

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
