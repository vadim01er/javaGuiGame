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

    public Bullet(double playerX, double playerY){
        bullet = new Rectangle(10, 5, Color.RED);
        bullet.setX(playerX + 20);
        bullet.setY(playerY);
    }



    public static void isHit(){
        for (Bullet bullet1: ControllerGame.bullets){
            for (Pair wall : ControllerGame.walls){
                Rectangle rect = (Rectangle) wall.getKey();
                if (bullet1.getRectangle().getBoundsInParent().intersects(rect.getBoundsInParent())){
                    ControllerGame.root.getChildren().remove(bullet1.getRectangle());
                    ControllerGame.bullets.remove(bullet1);
                    ControllerGame.root.getChildren().remove(rect);
                    ControllerGame.walls.remove(wall);
                    System.out.println("hit");
                    return;
                }
            }
        }
    }


}
