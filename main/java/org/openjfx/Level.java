package org.openjfx;

import java.util.ArrayList;

class Level {

    static ArrayList<ArrayList<String>> levels = new ArrayList<>();
    private static ArrayList<String> oneLevel = new ArrayList<>();

    static void createLevel(int size) {
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
