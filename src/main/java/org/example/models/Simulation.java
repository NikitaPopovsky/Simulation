package org.example.models;

import org.example.utils.Render;
import org.example.enums.Commands;
import org.example.models.actions.Action;

import java.util.*;

public class Simulation {
    private final GameMap gameMap;
    private int countStep;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(GameMap gameMap, List<Action> initActions, List<Action> turnActions) {
        this.gameMap = gameMap;
        this.countStep = 0;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation () {
        Commands command = Commands.EMPTY;
        Thread thread = null;
        Render.printStartMessage();
        while (command != Commands.EXIT) {
            command = getCommand();

            if (thread == null || thread.isInterrupted()) {
                thread = new ThreadSimulation(this);
            }
            if (command == Commands.START && !thread.isAlive() ) {
                thread.start();
            } else if (command == Commands.PAUSE && thread.isAlive()) {
                thread.interrupt();
            } else if (command == Commands.EXIT) {
                continue;
            } else {
                Render.printIncorrectCommand();
            }
        }
    }

    public void nextTurn(){
        List<Action> actions = null;
        if (countStep == 0) {
            actions = initActions;
        } else {
            actions = turnActions;
        }

        for (Action action: actions) {
            action.make(gameMap);
        }
        Render.printMap(gameMap, countStep);
        countStep++;
        waitAfterStep();
    }

    private void waitAfterStep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Render.printPause();
        }
    }

    private Commands getCommand() {
        Scanner scan = new Scanner(System.in);
        Render.printCommands();
        String message = scan.next().toLowerCase();
        return Commands.getByValue(message);
    }
}
