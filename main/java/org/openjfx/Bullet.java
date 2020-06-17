package org.openjfx;

import javafx.scene.paint.Color;

class Bullet extends CoordinateObject {

    private final int speed = 3;

    Bullet(double playerX, double playerY) {
        super(playerX, playerY,10, 5, Color.RED);
    }

    void update() {
        this.setX(this.getX() + speed);
    }

    boolean isHit(Wall wall) {
        return this.isCollision(wall);
    }

    boolean checkOutOfBounds(double rootX, double rootWidth) {
        return this.getX() > rootWidth - rootX;
    }

}
