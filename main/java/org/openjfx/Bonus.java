package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bonus extends Pane {

    private static Rectangle removeBonus = null;

    public static void bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 1000);
        int y = (int) Math.floor(Math.random() * 400);
        if (random == 5) {
            Rectangle rect = new Rectangle(5, 5, Color.RED);
            rect.setX(x);
            rect.setY(y);
            ControllerGame.bonuses.add(rect);
            ControllerGame.root.getChildren().add(rect);
        }
    }

    static void isBonus() {
        ControllerGame.bonuses.forEach((rect) -> {
            if (ControllerGame.player.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeBonus = rect;
                Player.score++;
                System.out.println(Player.score);
            }
        });
        ControllerGame.bonuses.remove(removeBonus);
        ControllerGame.root.getChildren().remove(removeBonus);
    }
}
