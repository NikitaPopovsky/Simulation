package org.example.models;

import org.example.Render;
import org.example.models.actions.Action;

import java.util.List;

public class Simulation {
    private static Simulation instance;
    private GameMap gameMap;
    private int countStep;
    private List<Action> Actions;


    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private Simulation() {
        gameMap = GameMap.getInstance();
        countStep = 0;

    }

    public void nextTurn(){
        if (countStep == 1) {
            Action.initActions(gameMap);
        }

    }

    public void startSimulation (int endCountStep) {

        while (countStep++ <  endCountStep) {
            nextTurn();
            Render.printMap(gameMap, countStep);
        }
        pauseSimulation ();
    }

    public void pauseSimulation () {
        System.exit(0);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
