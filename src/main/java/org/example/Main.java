package org.example;

import org.example.models.GameMap;
import org.example.models.Simulation;

public class Main {
    public static void main(String[] args) {
        //Количество полей в ширину, высоту
        int countPosition = 10;
        int countEntity = 5;
        //Количество ходов симуляции
        int countStep = 10;

        Simulation simulation = Simulation.getInstance();
        initializationGameMap(simulation.getGameMap(), countPosition, countEntity);

        simulation.startSimulation(countStep);

    }

    private static void initializationGameMap(GameMap gameMap, int countPosition, int countEntity) {
        gameMap.setWidth(countPosition);
        gameMap.setHeight(countPosition);
        gameMap.setCountEachEntity(countEntity);

    }
}