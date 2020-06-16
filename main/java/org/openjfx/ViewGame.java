package org.openjfx;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class ViewGame {

    private Pane root;
    private ModelGame modelGame;
    private Stage stage;

    boolean startGame = true;

    ViewGame(Stage stage){
        this.stage = stage;
    }

    private void init(){
        root = new Pane();
        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        modelGame = new ModelGame();
        modelGame.init();
        ControllerGame controllerGame = new ControllerGame(scene, modelGame);
        controllerGame.addListener();
    }

    void startNewGame(){
        init();
        start();
    }

    private void rewrite(){
        root.getChildren().clear();

        root.setLayoutX(modelGame.getRootX());
        root.setLayoutY(modelGame.getRootY());

        Player player = modelGame.getPlayer();
        Rectangle playerRoot = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        playerRoot.setFill(player.getColor());
        root.getChildren().add(playerRoot);

        for (Wall wall: modelGame.getEndLine()){
            Rectangle wallRoot = new Rectangle(wall.getX(), wall.getY(),wall.getWidth(), wall.getHeight());
            wallRoot.setFill(wall.getColor());
            root.getChildren().add(wallRoot);
        }

        for (Wall wall : modelGame.getWalls()) {
            Rectangle wallRoot = new Rectangle(wall.getX(), wall.getY(),wall.getWidth(), wall.getHeight());
            wallRoot.setFill(wall.getColor());
            root.getChildren().add(wallRoot);
        }

        for (Bullet bullet : modelGame.getBullets()) {
            Rectangle bulletRoot = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            bulletRoot.setFill(bullet.getColor());
            root.getChildren().add(bulletRoot);
        }

        for (Bonus bonus: modelGame.getBonuses()){
            Rectangle wallRoot = new Rectangle(bonus.getX(), bonus.getY(),bonus.getWidth(), bonus.getHeight());
            wallRoot.setFill(bonus.getColor());
            root.getChildren().add(wallRoot);
        }
    }

    void start() {
        startGame = true;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (startGame){
                    modelGame.updateGame();
                    rewrite();
                }
                startGame = modelGame.startGame;
            }
        };
        timer.start();
    }

//    private void continueMenu() throws IOException {
//        App app = new App();
//        app.start(stage);
//        root.getChildren().addAll();
//        Scene scene = new Scene(root, 500, 400);
//        stage.setScene(scene);
////        controllerContinue.setStage(stage);
//    }

    void setStage(Stage stage) {
        this.stage = stage;
    }
}
