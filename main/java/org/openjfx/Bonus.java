package org.openjfx;

import javafx.scene.paint.Color;

class Bonus {

    private double x;
    private double y;
    private int width = 10;
    private int height = 10;
    private Color color = Color.AQUA;

    Bonus(int i, int j) {
        this.x = i * width * 4;
        this.y = j * height * 4;
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

    double getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
