package org.example.models;

import org.example.enums.Constants;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

public final class EntityFactory {
    public static Entity create(Class<? extends Entity>  entityClass) {
        if (entityClass == Rock.class) {
            return new Rock();
        } else if (entityClass == Tree.class) {
            return new Tree();
        } else if (entityClass == Grass.class) {
            return new Grass();
        } else if (entityClass == Herbivore.class) {
            return new Herbivore(Constants.SPEED.getValue(), Constants.HEALTH.getValue());
        } else if (entityClass == Predator.class) {
            return new Predator(Constants.SPEED.getValue(), Constants.POWER.getValue());
        }
        return null;
    }
}
