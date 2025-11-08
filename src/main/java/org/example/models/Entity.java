package org.example.models;

import org.example.TypeEntity;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import static org.example.TypeEntity.ROCK;

public abstract class Entity {
    public static Entity createEntity (TypeEntity type) {
        switch (type) {
            case ROCK :
                return new Rock();
            case TREE:
                return new Tree();
            case GRASS:
                return new Grass();
            case HERBIVORE:
                return new Herbivore();
            case PREDATOR:
                return new Predator();
            default:
                return null;
        }
    }
}
