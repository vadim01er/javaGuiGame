package org.openjfx;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Level extends Pane {

    public static ArrayList<ArrayList<String>> levels = new ArrayList<>();
    public static ArrayList<String> oneLevel = new ArrayList<>();

    public static void createLevel(int size) {
//        for (int i = 0; i < 20; i++) {
//            StringBuilder line = new StringBuilder();
//            if (i == 0 || i == 19) line.append("1".repeat(20));
//            else {
//                if (exit[3] && (i == 8 || i==9 || i ==10)) line.append("2");
//                else line.append("1");
//                line.append("0".repeat(18));
//                if (exit[1] && (i == 8 || i==9 || i ==10)) line.append("2");
//                else line.append("1");
//            }
//            oneLevel.add(line.toString());
//        }
//        levels.add(oneLevel);

        oneLevel.add("1111111111");
        for (int i = 0; i < 5; i++) oneLevel.add("1000000001");
        for (int i = 0; i < size - 10; i++) {
            StringBuilder line = new StringBuilder("1");
            for (int j = 0; j < 8; j++) {
                int wall = (int) Math.floor(Math.random() * 7);
                if (wall == 5) line.append("1");
                else line.append("0");
            }
            line.append("1");
            oneLevel.add(line.toString());
        }
        for (int i = 0; i < 5; i++) oneLevel.add("1000000001");
        oneLevel.add("1111111111");
        levels.add(oneLevel);
    }
}
