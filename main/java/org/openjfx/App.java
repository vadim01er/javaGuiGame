package org.openjfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * JavaFX App
 */
public class App extends Application {

    private Double Xmouse;
    private Double Ymouse;
    static Map<KeyCode, Boolean> keys = new HashMap<>();
    public static Pane root = new Pane();
    private Scene scene = new Scene(root, 300, 300);
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    public static ArrayList<Rectangle> walls = new ArrayList<>();
    private int wallWeight = 40;
    int size = 10;
    private int[][] createWall = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int speed = 1;
    private double start = 0.0;
    private boolean startGame = false;

    static Player player = new Player(20, Color.AQUA);

    private void update() {
        if (isPressed(KeyCode.DOWN)) {
            player.setScaleY(speed);
            player.move(Player.Axis.Y, speed);
        }
        if (isPressed(KeyCode.UP)) {
            player.setScaleY(-speed);
            player.move(Player.Axis.Y, -speed);
        }
        if (isPressed(KeyCode.LEFT)) {
            player.setScaleX(-speed - 4);
            player.move(Player.Axis.X, -speed - 4);
        }
        if (isPressed(KeyCode.RIGHT)) {
            player.setScaleX(speed);
            player.move(Player.Axis.X, speed);
        }
        player.setScaleX(speed);
        player.move(Player.Axis.X, speed);
    }

    private boolean isPressed(KeyCode keyCode) {
        return keys.getOrDefault(keyCode, false);
    }

    @Override
    public void init() {
        for (int i = 0; i < createWall.length; i++) {
            for (int j = 0; j < createWall[0].length; j++) {
                if (createWall[i][j] != 0) {
                    Rectangle rect = new Rectangle(10 * 4, 10 * 2, Color.DARKGRAY);
                    rect.setTranslateX(j * size * 4);
                    rect.setTranslateY(60 + i * size * 2);
                    walls.add(rect);
                }
            }
        }
        root.getChildren().addAll(walls);
        root.getChildren().addAll(player);
        player.setTranslateX(scene.getWidth() / 2 - player.getR() / 2);
        player.setTranslateY(scene.getHeight() / 2 - player.getR() / 2);
        root.setLayoutX(start);
        System.out.println(scene.getHeight() / 2);
        root.setLayoutY(0.0);
    }

    private void updateRoot() {
        root.setLayoutX(root.getLayoutX() - speed);
    }

    @Override
    public void start(Stage stage) {
        player.translateYProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            root.setLayoutY(-(offset - (scene.getHeight() / 2 - player.getR() / 2)));
        });
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) startGame = !startGame;
            keys.put(keyEvent.getCode(), true);
        });
        scene.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(), false));
//        scene.setOnMouseClicked(mouseEvent ->  isBullet = true);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (startGame) {
//                  updateBullet();
                    updateRoot();
                    update();
                    Bonus.bonus();
                }
            }
        };
        timer.start();
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}