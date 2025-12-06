package org.example;

import org.example.models.Simulation;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = Simulation.getInstance();
        simulation.startSimulation();
    }
}