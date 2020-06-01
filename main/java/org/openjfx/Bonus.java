package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bonus extends Pane {

    private Rectangle bonus;

    public Rectangle getBonus() {
        return bonus;
    }

    public Bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 1000);
        int y = (int) Math.floor(Math.random() * 400);
        if (true/*random == 5*/) {
            bonus = new Rectangle(5, 5, Color.RED);
            bonus.setX(x);
            bonus.setY(y);
            getChildren().add(bonus);
        }
    }

    public void isBonus() {
        if (ModelGame.player.getBoundsInParent().intersects(this.getBonus().getBoundsInParent())) {
            ModelGame.bonuses.remove(this);
            ModelGame.root.getChildren().remove(this);
            Player.score++;
            System.out.println(Player.score);
        }
    }
}
