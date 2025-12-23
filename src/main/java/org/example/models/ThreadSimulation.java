package org.example.models;

public class ThreadSimulation extends Thread{
    private final Simulation simulation;

    @Override
    public void run () {
        while (!this.isInterrupted()) {
            simulation.nextTurn();
        }
    }

    public ThreadSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
