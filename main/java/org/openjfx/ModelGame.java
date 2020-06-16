package org.openjfx;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class ModelGame {

    Map<KeyCode, Boolean> keys = new HashMap<>();
    private ArrayList<Bonus> bonuses = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Wall> stopLine = new ArrayList<>();
    Level level = new Level();

    int speed = 1;
    private double forBullet = 1.0;
    private Player player;
    private int levelNumber = 0;
    private int lengthLevel  = 20;
    boolean startGame = true;

    private double rootX = 0.0;
    private double rootY = 0.0;
    private double rootHeight = 400.0;
    private double rootWidth = 500.0;

    void init() {
        level.createLevel(lengthLevel);
        level.createWall(levelNumber);
        walls = level.getWalls();
        bonuses = level.getBonuses();
        stopLine = level.getStopLine();
        player = new Player( 50, rootHeight / 2,20, 20, Color.AQUA);
    }

    void updateGame() {
        updateBullet();
        updateRoot();
        updatePlayer();
        checkEndLevel();
    }

    private void updatePlayer() {
        if (isPressed(KeyCode.DOWN)) player.moveY(speed, walls);
        if (isPressed(KeyCode.UP)) player.moveY(-speed, walls);
        if (isPressed(KeyCode.LEFT)) player.moveX(-speed, rootX, rootWidth, walls);
        if (isPressed(KeyCode.RIGHT)) player.moveX(speed, rootX, rootWidth, walls);
        for (Iterator i = bonuses.iterator(); i.hasNext();){
            Bonus bonus = (Bonus) i.next();
            if (player.checkBonus(bonus)){
                i.remove();
            }
        }
        if (player.checkOutOfLevel(rootX)) {
            player.setScore(1);
            player.setX(50);
            player.setY(rootHeight / 2);
            rootX = 0.0;
        }
    }

    private boolean isPressed(KeyCode keyCode) { return keys.getOrDefault(keyCode, false); }

    private void createBullet(){
        forBullet += player.getScore() / 2;
        if (forBullet > 60) {
            forBullet = 1.0;
            Bullet bullet = new Bullet(player.getX() + (double) player.getWidth() / 2,
                    player.getY() + (double) player.getHeight() / 2);
            bullets.add(bullet);
        }
    }

    private void updateBullet() {
        for (Iterator bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
            Bullet bullet = (Bullet) bulletIterator.next();
            bullet.update();
            for (Iterator j = walls.iterator(); j.hasNext();) {
                Wall wall = (Wall) j.next();
                if (bullet.isHit(wall)) {
                    bulletIterator.remove();
                    int health = wall.getHealth() - 1;
                    if (health == 0) j.remove();
                    else wall.setHealth(health);
                    return;
                }
            }
            if (bullet.checkOutOfBounds(rootX, rootWidth)) bulletIterator.remove();
        }
    }

    private void updateRoot() {
        createBullet();
        rootX -= speed;
        player.moveX(speed, rootX, rootWidth, walls);
    }

    private void checkEndLevel(){
        if (player.getX() + player.getWidth() > stopLine.get(0).getX()){
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
            level.createWall(levelNumber);
            walls = level.getWalls();
            bonuses = level.getBonuses();
            stopLine = level.getStopLine();
        }
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
