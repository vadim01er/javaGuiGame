package org.openjfx;

import javafx.scene.paint.Color;

class CoordinateObject {

    private double x;
    private double y;
    private Color color;
    private int width;
    private int height;

    CoordinateObject(double x, double y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    Color getColor() {
        return color;
    }

    void setX(double x) {
        this.x = x;
    }

    void setY(double y) {
        this.y = y;
    }
}
