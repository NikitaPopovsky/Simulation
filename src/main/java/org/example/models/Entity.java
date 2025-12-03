package org.example.models;

import org.example.enums.TypeEntity;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

public abstract class Entity {
    public static Entity createEntity (TypeEntity type) {
        return switch (type) {
            case ROCK -> new Rock();
            case TREE -> new Tree();
            case GRASS -> new Grass();
            case HERBIVORE -> new Herbivore();
            case PREDATOR -> new Predator();
            default -> null;
        };
    }

}
