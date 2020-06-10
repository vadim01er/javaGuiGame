package org.openjfx;

import javafx.scene.paint.Color;

public class Bonus {

    private double x;
    private double y;
    private Color color = Color.GOLD;

    public Bonus() {
//        int random = (int) Math.floor(Math.random() * 100);
        int x = (int) Math.floor(Math.random() * 1000);
        int y = (int) Math.floor(Math.random() * 400);
        this.x = x;
        this.y = y;
    }

//    public void isBonus() {
//        if (ModelGame.player.getBoundsInParent().intersects(this.getBonus().getBoundsInParent())) {
//            ModelGame.bonuses.remove(this);
//            ModelGame.root.getChildren().remove(this);
//            Player.score++;
//            System.out.println(Player.score);
//        }
//    }
}
