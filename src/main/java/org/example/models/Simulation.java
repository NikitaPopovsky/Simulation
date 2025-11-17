package org.example.models;

import java.util.List;

public class Simulation {
    private static Simulation instance;
    private int countStep;
    private List<Action> Actions;


    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    private Simulation() {
    }

    public void nextTurn(){

    }

    public void startSimulation () {

    }

    public void pauseSimulation () {

    }
}
