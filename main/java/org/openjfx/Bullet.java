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

    int getSpeed() {
        return speed;
    }

    public Rectangle getBullet() {
        return bullet;
    }

    Bullet(double playerX, double playerY) {
        bullet = new Rectangle(10, 5, Color.RED);
        bullet.setX(playerX + 20);
        bullet.setY(playerY);
        getChildren().add(bullet);
    }

    void update(){
        this.getBullet().setTranslateX(this.getBullet().getTranslateX() + speed);
    }

    void isHit() {
        for (Pair<Rectangle, Integer> wall : ModelGame.walls) {
            Rectangle rect = wall.getKey();
            if (this.getBullet().getBoundsInParent().intersects(rect.getBoundsInParent())) {
                int health = wall.getValue();
                ModelGame.root.getChildren().remove(this);
                ModelGame.bullets.remove(this);
                ModelGame.walls.remove(wall);
                if (health == 0) {
                    ModelGame.root.getChildren().remove(rect);
                    return;
                }
                health -= 1;
                ModelGame.walls.add(new Pair<>(rect, health));
                return;
            }
        }
    }

    void checkOutOfBounds(){
        if (this.getBullet().getTranslateX() > Math.abs(ModelGame.root.getLayoutX() + ModelGame.root.getWidth() + 10)) {
            System.out.println(this.getBullet().getTranslateX());
            System.out.println(ModelGame.root.getLayoutX() + ModelGame.root.getWidth());
            ModelGame.bullets.remove(this);
            ModelGame.root.getChildren().remove(this);
        }
    }
}
