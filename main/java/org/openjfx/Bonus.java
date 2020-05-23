package org.openjfx;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bonus extends Pane {

    private static Rectangle removeBonus = null;

    public static void bonus() {
        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 300);
        int y = (int) Math.floor(Math.random() * 300);
        if (random == 5) {
            Rectangle rect = new Rectangle(5, 5, Color.RED);
            rect.setX(x);
            rect.setY(y);
            App.bonuses.add(rect);
            App.root.getChildren().addAll(rect);
        }
    }

    static void isBonus() {
        App.bonuses.forEach((rect) -> {
            if (App.player.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                removeBonus = rect;
                Player.score++;
                System.out.println(Player.score);
            }
        });
        App.bonuses.remove(removeBonus);
        App.root.getChildren().remove(removeBonus);
    }
}
