package org.openjfx;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelGame {

    Map<KeyCode, Boolean> keys = new HashMap<>();
    static ArrayList<Bonus> bonuses = new ArrayList<>();
    static ArrayList<Wall> walls = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();

    int speed;
    private int forBullet = 1;
    private Player player;
    private int levelNumber = 0;
    private int lengthLevel  = 100;

    private double rootX = 0.0;
    private double rootY = 0.0;

    private double rootHeight;
    private double rootWidth;

    ModelGame(double rootHeight, double rootWidth) {
        this.rootHeight = rootHeight;
        this.rootWidth = rootWidth;
    }

    void init() {
        speed = 1;
        Level.createLevel(lengthLevel);
        Wall.createWall(levelNumber);
        player = new Player(20, 50, rootHeight / 2, Color.AQUA);
    }

    private void updatePlayer() {
        if (isPressed(KeyCode.DOWN)) player.moveY(speed);
        if (isPressed(KeyCode.UP)) player.moveY(-speed);
        if (isPressed(KeyCode.LEFT)) player.moveX(-speed);
        if (isPressed(KeyCode.RIGHT)) player.moveX(speed);
    }

    private boolean isPressed(KeyCode keyCode) { return keys.getOrDefault(keyCode, false); }

    private void createBonus() {
        Bonus bonus = new Bonus();
        bonuses.add(bonus);
    }

    private void updateBonus(){
        for (int i = 0; i < bonuses.size(); i++){
//            bonuses.get(i).isBonus();
        }
    }

    private void createBullet(){
        forBullet += 1;
        if (forBullet % 30 == 0) {
            forBullet = 1;
            Bullet bullet = new Bullet(player.getX() + (double) player.getSize() / 2,
                    player.getY() + (double) player.getSize() / 2);
            bullets.add(bullet);
        }
    }

    private void updateBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bul = bullets.get(i);
            bul.update();
            bul.isHit();
            bul.checkOutOfBounds(rootX, rootWidth);
        }
    }

    private void updateRoot() {
        createBullet();
        rootX -= speed;
        player.moveX(speed);
    }

    void updateGame() {
//        player.translateXProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutX((root.getWidth() / 2) - player.getSize() - offset);
//        });
//        player.translateYProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutY((root.getHeight() / 2) - player.getSize() - offset);
//        });
        updateBullet();
        updateRoot();
        updatePlayer();
//        createBonus();
//        updateBonus();
    }

    ArrayList<Bonus> getBonuses() {
        return bonuses;
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    ArrayList<Bullet> getBullets() {
        return bullets;
    }

    Player getPlayer() {
        return player;
    }

    double getRootX() {
        return rootX;
    }

    double getRootY() {
        return rootY;
    }
}
