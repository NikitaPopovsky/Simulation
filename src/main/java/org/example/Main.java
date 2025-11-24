package org.example;

import org.example.models.GameMap;
import org.example.models.Simulation;

public class Main {
    public static void main(String[] args) {
        //Количество полей в ширину, высоту, количество каждой сущности
        int countForMap = 5;
        //Количество ходов симуляции
        int countStep = 10;

        Simulation simulation = Simulation.getInstance();
        initializationGameMap(simulation.getGameMap(), countForMap);

        simulation.startSimulation(countStep);

    }

    private static void initializationGameMap(GameMap gameMap, int count) {
        gameMap.setWidth(count);
        gameMap.setHeight(count);
        gameMap.setCountEachEntity(count);

    }
}