package org.example;

import org.example.enums.Constants;
import org.example.models.GameMap;
import org.example.models.Simulation;
import org.example.models.actions.ActionFactory;


public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap(Constants.WIDTH_MAP.getValue(), Constants.HEIGHT_MAP.getValue());
        ActionFactory actionFactory =  new ActionFactory();
        Simulation simulation = new Simulation(gameMap, actionFactory.createInit(), actionFactory.createTurn());
        simulation.startSimulation();
    }
}