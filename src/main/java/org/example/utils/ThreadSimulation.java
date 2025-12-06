package org.example.utils;

import org.example.models.Simulation;

public class ThreadSimulation extends Thread{
    private final Simulation simulation;

    @Override
    public void run () {
        while (!this.isInterrupted()) {
            simulation.makeStep();
        }
    }

    public ThreadSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
