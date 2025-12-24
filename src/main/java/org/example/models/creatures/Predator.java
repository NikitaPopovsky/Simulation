package org.example.models.creatures;

import org.example.models.Coordinate;
import org.example.models.GameMap;

public class Predator extends Creature {
    private final int power;

    @Override
    public void attack(Coordinate coordinate, GameMap gameMap) {
        Herbivore target = (Herbivore) gameMap.getEntity(coordinate);
        target.getDamage(power);
        if (target.getHealth() == 0) {
            gameMap.removeEntity(coordinate);
        }
    }

    public Predator(int speed, int power) {
        super(speed);
        this.power = power;
    }

}

