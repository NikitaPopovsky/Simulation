package org.example.models.creatures;

import org.example.models.Entity;
import org.example.models.textures.Grass;

public abstract class Creature extends Entity {
    public Class<? extends Entity> getTargetClass () {
        if (this instanceof Herbivore) {
            return Grass.class;
        } else if (this instanceof Predator) {
            return Herbivore.class;
        }
        return null;
    }
}
