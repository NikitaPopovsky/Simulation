package org.example;

import org.example.models.GameMap;
import org.example.models.Render;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(10, 10, 5);
        gameMap.generateCoordinates();
        Render.printMap(gameMap);
    }
}