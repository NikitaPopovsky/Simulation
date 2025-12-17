package org.example;

import org.example.models.Entity;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

public final class EntityFactory {
    public static Entity create(Class<?> entityClass) {
        if (entityClass == Rock.class) {
            return new Rock();
        } else if (entityClass == Tree.class) {
            return new Tree();
        } else if (entityClass == Grass.class) {
            return new Grass();
        } else if (entityClass == Herbivore.class) {
            return new Herbivore();
        } else if (entityClass == Predator.class) {
            return new Predator();
        }
        return null;
    }
}
