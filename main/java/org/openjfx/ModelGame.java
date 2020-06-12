package org.openjfx;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelGame {

    Map<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<Bonus> bonuses = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Wall> stopLine = new ArrayList<>();
    Level level = new Level();

    int speed = 1;
    private double forBullet = 0.0;
    private Player player;
    private int levelNumber = 0;
    private int lengthLevel  = 20;

    private double rootX = 0.0;
    private double rootY = 0.0;

    private double rootHeight = 400.0;
    private double rootWidth = 500.0;

    void init() {
        level.createLevel(lengthLevel);
        level.createWall(levelNumber, walls, bonuses, stopLine);
        player = new Player(20, 50, rootHeight / 2, Color.AQUA);
    }

    private void updatePlayer() {
        if (isPressed(KeyCode.DOWN)) player.moveY(speed, walls);
        if (isPressed(KeyCode.UP)) player.moveY(-speed, walls);
        if (isPressed(KeyCode.LEFT)) player.moveX(-speed, walls);
        if (isPressed(KeyCode.RIGHT)) player.moveX(speed, walls);
        player.checkBonus(bonuses);
    }

    private boolean isPressed(KeyCode keyCode) { return keys.getOrDefault(keyCode, false); }

    private void createBullet(){
        forBullet += player.getScore() / 2;
        if (forBullet > 60) {
            forBullet = 0.0;
            Bullet bullet = new Bullet(player.getX() + (double) player.getSize() / 2,
                    player.getY() + (double) player.getSize() / 2);
            bullets.add(bullet);
        }
    }

    private void updateBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            bullet.isHit();
            bullet.checkOutOfBounds(rootX, rootWidth);
        }
    }

    private void updateRoot() {
        createBullet();
        rootX -= speed;
        player.moveX(speed, walls);
    }

    private void checkEndLevel(){
        if (player.getX() + player.getSize() > stopLine.get(0).getX()){
            rootX = 0.0;
            lengthLevel += lengthLevel;
            levelNumber++;
            walls.clear();
            bonuses.clear();
            bullets.clear();
            stopLine.clear();
            player.setX(50);
            player.setY(rootHeight / 2);
            level.createLevel(lengthLevel);
            level.createWall(levelNumber, walls, bonuses, stopLine);
        }
    }

    void updateGame() {
        updateBullet();
        updateRoot();
        updatePlayer();
        checkEndLevel();
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

    ArrayList<Wall> getEndLine() {
        return stopLine;
    }
}
//        player.translateXProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutX((root.getWidth() / 2) - player.getSize() - offset);
//        });
//        player.translateYProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutY((root.getHeight() / 2) - player.getSize() - offset);
//        });
