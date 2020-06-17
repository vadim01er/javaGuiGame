package org.openjfx;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class Level {

    private ArrayList<ArrayList<String>> levels = new ArrayList<>();

    private ArrayList<Bonus> bonuses = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Wall> stopLine = new ArrayList<>();

    void createLevel(int size) {
        ArrayList<String> oneLevel = new ArrayList<>();
        oneLevel.add("1111111111");
        for (int i = 0; i < 5; i++) oneLevel.add("1000000001");
        for (int i = 0; i < size - 11; i++) {
            StringBuilder line = new StringBuilder("1");
            for (int j = 0; j < 8; j++) {
                int isWall = (int) Math.floor(Math.random() * 20);
                if (isWall < 5) line.append("1");
                else if (isWall == 5) line.append("2");
                else line.append("0");
            }
            line.append("1");
            oneLevel.add(line.toString());
        }
        for (int i = 0; i < 4; i++) oneLevel.add("1000000001");
        oneLevel.add("1333333331");
        for (int i = 0; i < 15; i++) oneLevel.add("1000000001");
        oneLevel.add("1111111111");
        levels.add(oneLevel);
    }

    void createLevelForTest(){
        ArrayList<String> oneLevel = new ArrayList<>();
        oneLevel.add("1111111111");
        for (int i = 0; i < 2; i++) {
            oneLevel.add("1000000001");
        }
        oneLevel.add("1111111111");
        levels.add(oneLevel);
    }

    void createWall(int levelNumber){
        ArrayList<String> nowLevel = levels.get(levelNumber);
        for (int i = 0; i < nowLevel.size(); i++) {
            for (int j = 0; j < nowLevel.get(0).length(); j++) {
                switch (nowLevel.get(i).charAt(j)){
                    case '1': {
                        Wall wall = new Wall(i, j, 40 , 40);
                        walls.add(wall);
                        break;
                    }
                    case '2': {
                        Bonus bonus = new Bonus(i * 40,j * 40, 10, 10);
                        bonuses.add(bonus);
                        break;
                    }
                    case '3': {
                        Wall wall = new Wall(i, j,40,40, Color.GOLD);
                        stopLine.add(wall);
                        break;
                    }
                    default:break;
                }
            }
        }
    }

    ArrayList<Bonus> getBonuses() {
        return bonuses;
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    ArrayList<Wall> getStopLine() {
        return stopLine;
    }
}
