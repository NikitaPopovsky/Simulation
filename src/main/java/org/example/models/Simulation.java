package org.example.models;

import org.example.Render;
import org.example.models.actions.Action;

import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
    private static Simulation instance;
    private GameMap gameMap;
    private int countStep;
    private final Queue<Action> actions = new LinkedList<>();


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

    private void addAction(){
        if (countStep == 0) {
            Action.addInitActions(actions);
            return;
        }
        Action.addTurnActions(actions);
    }

    private void nextTurn(){

        while (!actions.isEmpty()) {
           actions.poll().make();
       }
    }



    public void startSimulation (int endCountStep) {

        while (countStep <  endCountStep) {
            addAction();
            nextTurn();
            Render.printMap(gameMap, countStep);
            countStep++;
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
