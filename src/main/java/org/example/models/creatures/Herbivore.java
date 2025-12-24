package org.example.models.creatures;

import org.example.models.Coordinate;
import org.example.models.GameMap;

public class Herbivore extends Creature {
    private int health;

    public Herbivore(int speed, int health) {
        super(speed);
        this.health = health;
    }

    @Override
    public void attack(Coordinate coordinate, GameMap gameMap) {
        gameMap.removeEntity(coordinate);
    }

    public int getHealth() {
        return health;
    }

    public void getDamage (int power) {
        int totalHealth = health - power;
        health = Math.max(0,totalHealth);
    }
}


