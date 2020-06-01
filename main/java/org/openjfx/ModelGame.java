package org.openjfx;


import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModelGame {

    static Pane root;

    static Map<KeyCode, Boolean> keys = new HashMap<>();
    static ArrayList<Bonus> bonuses = new ArrayList<>();
    static ArrayList<Pair<Rectangle, Integer>> walls = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();

    static int speed;
    static boolean startGame = true;

    static Player player;

    private int levelNumber = 0;

    ModelGame(Pane root) {
        this.root = root;
    }

    void init() {
        speed = 1;
        Level.createLevel(100);
        ArrayList<String> nowLevel = Level.levels.get(levelNumber);
        double sizeWallHeight = 40;
        double sizeWallWidth = 40;
        for (int i = 0; i < nowLevel.size(); i++) {
            for (int j = 0; j < nowLevel.get(0).length(); j++) {
                if (nowLevel.get(i).charAt(j) == '1') {
                    Rectangle rect = new Rectangle(sizeWallWidth, sizeWallHeight, Color.DARKGRAY);
                    rect.setTranslateX(i * sizeWallWidth);
                    rect.setTranslateY(j * sizeWallHeight);
                    root.getChildren().addAll(rect);
                    walls.add(new Pair<>(rect, 1 + (int) Math.floor(Math.random() * 4)));
                }
            }
        }
        player = new Player(20, Color.AQUA);
        root.getChildren().addAll(player);
        player.setTranslateX(sizeWallWidth + player.getSize());
        player.setTranslateY(root.getHeight() / 2 - player.getSize());
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
        root.getChildren().add(bonus);
        bonuses.add(bonus);
    }

    void createBullet(){
        Bullet bullet = new Bullet(ModelGame.player.getTranslateX() + (double) ModelGame.player.getSize() / 2,
                ModelGame.player.getTranslateY() + (double) ModelGame.player.getSize() / 2);
        root.getChildren().add(bullet);
        bullets.add(bullet);
    }

    private void updateBullet() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bul = bullets.get(i);
            bul.update();
            bul.isHit();
//            bul.checkOutOfBounds();
        }
    }

    private void updateBonus(){
        for (int i = 0; i < bonuses.size(); i++){
            System.out.println();
            bonuses.get(i).isBonus();
        }
    }

    private void updateRoot() {
        root.setLayoutX(root.getLayoutX() - speed);
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
        createBonus();
        updateBonus();
    }

}
