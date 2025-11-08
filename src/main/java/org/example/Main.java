package org.example;

import org.example.models.GameMap;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(30, 30, 5);
        gameMap.generateCoordinates();
    }
}