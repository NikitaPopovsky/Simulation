package org.example.models.creatures;

import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.textures.Grass;

public abstract class Creature extends Entity {
    private boolean isBusy;
    private final int speed;

    public abstract void attack(Coordinate neighbor, GameMap gameMap);

    public Creature(int speed) {
        this.isBusy = false;
        this.speed = speed;
    }

    public Class<? extends Entity> getTargetClass () {
        if (this instanceof Herbivore) {
            return Grass.class;
        } else if (this instanceof Predator) {
            return Herbivore.class;
        }
        return null;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy() {
        isBusy = true;
    }

    public void clearBusy() {
        isBusy = false;
    }

    public int getSpeed() {
        return speed;
    }
}
