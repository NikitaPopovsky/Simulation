package org.example.models;

import org.example.utils.Render;
import org.example.utils.ThreadSimulation;
import org.example.enums.Commands;
import org.example.models.actions.Action;

import java.util.*;

//Класс симуляции
public class Simulation {
    private static Simulation instance;
    private final GameMap gameMap;
    private int countStep;
    private final Queue<Action> actions;


    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private Simulation() {
        gameMap = GameMap.getInstance();
        countStep = 0;
        actions = new LinkedList<>();
    }

    //Добавляем стартовые и регулярные действия
    private void addAction(){
        if (countStep == 0) {
            Action.addInitActions(actions);
            return;
        }
        Action.addTurnActions(actions);
    }

    //Получаем действия и выполняем последовательно
    private void nextTurn(){
        while (!actions.isEmpty()) {
           actions.poll().make();
       }
    }

    //Делаем новый ход симуляции
    public void makeStep(){
        addAction();
        nextTurn();
        Render.printMap(gameMap, countStep);
        countStep++;
        waitAfterStep();
    }

    private void waitAfterStep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Render.showPause();
        }
    }

    //Стартуем симуляцию
    public void startSimulation () {
        Commands command = Commands.EMPTY;
        Thread thread = null;
        Render.showStartMessage();
        while (command != Commands.EXIT) {
            command = getCommand();

            if (thread == null || thread.isInterrupted()) {
                thread = new ThreadSimulation(instance);
            }
            if (command == Commands.START && !thread.isAlive() ) {
                thread.start();
            } else if (command == Commands.PAUSE && thread.isAlive()) {
                thread.interrupt();
            } else if (command == Commands.EXIT) {
                continue;
            } else {
                Render.showIncorrectCommand();
            }
        }
    }

    //Получаем команду от пользователя
    private Commands getCommand() {
        Scanner scan = new Scanner(System.in);
        Render.showCommands();
        String message = scan.next().toLowerCase();
        return Commands.getByValue(message);
    }
}
