package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Bullet extends Pane {

    private Rectangle bullet;

    private int speed = 3;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getRectangle() {
        return bullet;
    }

    public Bullet(double playerX, double playerY) {
        bullet = new Rectangle(10, 5, Color.RED);
        bullet.setX(playerX + 20);
        bullet.setY(playerY);
        getChildren().add(bullet);
    }


    public static void isHit() {
        for (Bullet bullet1 : ControllerGame.bullets) {
            for (Pair<Rectangle, Integer> wall : ControllerGame.walls) {
                Rectangle rect = wall.getKey();
                if (bullet1.getRectangle().getBoundsInParent().intersects(rect.getBoundsInParent())) {
                    int health = wall.getValue();
                    ControllerGame.root.getChildren().remove(bullet1.getRectangle());
                    ControllerGame.bullets.remove(bullet1);
                    ControllerGame.walls.remove(wall);
                    if (health == 0) {
                        ControllerGame.root.getChildren().remove(rect);
                        ControllerGame.walls.remove(wall);
                        return;
                    }
                    health -= 1;
                    ControllerGame.walls.add(new Pair<>(rect, health));
                    return;

                }
            }
        }
    }


}
