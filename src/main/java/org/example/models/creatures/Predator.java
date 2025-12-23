package org.example.models.creatures;

public class Predator extends Creature {
    private final int power;

    public Predator(int speed, int power) {
        super(speed);
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}

