package org.openjfx;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ControllerGame {


    private Map<KeyCode, Boolean> keys = new HashMap<>();
    static Pane root;
    private Scene scene;
    static ArrayList<Rectangle> bonuses = new ArrayList<>();
    static ArrayList<Pair<Rectangle, Integer>> walls = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    private int speed;
    private boolean startGame = true;

    static Player player = new Player(20, Color.AQUA);
    private int levelNumber = 0;

    private Stage stage;

    private void init() {
        speed = 1;
        root = new Pane();
        scene = new Scene(root, 500, 400);
        player = new Player(40, Color.AQUA);
        Level.createLevel(100);
        ArrayList<String> nowLevel = Level.levels.get(levelNumber);
        double sizeWallHeight = 40/*root.getWidth() / 10*/;
        double sizeWallWidth = 40/*root.getHeight() / nowLevel.get(0).length()*/;
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
        root.getChildren().addAll(player);
        player.setTranslateX(sizeWallWidth + player.getSize());
        player.setTranslateY(root.getHeight() / 2 - player.getSize());

        stage.setScene(scene);
    }

    private void update() {
        if (isPressed(KeyCode.DOWN)) player.moveY(speed);
        if (isPressed(KeyCode.UP)) player.moveY(-speed);
        if (isPressed(KeyCode.LEFT)) player.moveX(-speed);
        if (isPressed(KeyCode.RIGHT)) player.moveX(speed);
    }

    private boolean isPressed(KeyCode keyCode) {
        return keys.getOrDefault(keyCode, false);
    }

    private void updateRoot() {
        root.setLayoutX(root.getLayoutX() - speed);
        player.moveX(speed);
    }

    public void startNewGame(){
        init();
        start();
    }

    public void start() {
        startGame = true;
//        player.translateXProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutX((scene.getWidth() / 2) - player.getSize() - offset);
//        });
//        player.translateYProperty().addListener((obs, old, newValue) -> {
//            int offset = newValue.intValue();
//            root.setLayoutY((scene.getHeight() / 2) - player.getSize() - offset);
//        });
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                startGame = !startGame;
            }
            if (keyEvent.getCode() == KeyCode.SHIFT) speed = 2;
            if (keyEvent.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet(player.getTranslateX() + (double) player.getSize() / 2,
                    player.getTranslateY() + (double) player.getSize() / 2);
                ControllerGame.root.getChildren().add(bullet.getRectangle());
                ControllerGame.bullets.add(bullet);}
            keys.put(keyEvent.getCode(), true);
        });
        scene.setOnKeyReleased(keyEvent -> {
            keys.put(keyEvent.getCode(), false);
            if (keyEvent.getCode() == KeyCode.SHIFT) speed = 1;
        });
//        scene.setOnMouseClicked(mouseEvent -> {});

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (startGame) {
                    updateBullet();
                    updateRoot();
                    update();
                    Bullet.isHit();
                    Bonus.bonus();
                    Bonus.isBonus();
                }
            }
        };
        timer.start();
    }

    private void updateBullet() {
        for (Bullet bul : bullets) {
            bul.getRectangle().setTranslateX(bul.getRectangle().getTranslateX() + bul.getSpeed());
            if (bul.getRectangle().getTranslateX() == root.getLayoutX()){
                bullets.remove(bul);
                root.getChildren().remove(bul.getRectangle());
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
