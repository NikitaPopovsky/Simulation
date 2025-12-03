package org.example.models;

import org.example.Render;
import org.example.models.actions.Action;

import java.util.LinkedList;
import java.util.Queue;

//Класс симуляции
public class Simulation {
    private static Simulation instance;
    private final GameMap gameMap;
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

    //Добавляем стартовые и регулярные действия
    private void addAction(){
        if (countStep == 0) {
            Action.addInitActions(actions);
            return;
        }
        Action.addTurnActions(actions);
    }

    //Прокручиваем новый ход
    private void nextTurn(){

        while (!actions.isEmpty()) {
           actions.poll().make();
       }
    }

    //Стартуем симуляцию
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
}
