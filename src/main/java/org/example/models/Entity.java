package org.example.models;

import org.example.enums.TypeEntity;
import org.example.dto.Coordinate;
import org.example.models.creatures.Creature;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;
import org.example.models.textures.Rock;
import org.example.models.textures.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, List<Coordinate>> DistributeCoordinatesByType (List<Coordinate> coordinates) {
        Map<String, List<Coordinate>> entityCoordinates = new HashMap<>();
        List<Coordinate> creatureCoordinates = new ArrayList<>();
        List<Coordinate> grassCoordinates = new ArrayList<>();
        List<Coordinate> staticEntityCoordinates = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            if (coordinate.getEntity() instanceof Creature) {
                creatureCoordinates.add(coordinate);
            } else if (coordinate.getEntity() instanceof Grass) {
                grassCoordinates.add(coordinate);
            } else if (coordinate.getEntity() instanceof Rock || coordinate.getEntity() instanceof Tree) {
                staticEntityCoordinates.add(coordinate);
            }
        }

        entityCoordinates.put("creature", creatureCoordinates);
        entityCoordinates.put("grass", grassCoordinates);
        entityCoordinates.put("staticEntity", staticEntityCoordinates);

        return entityCoordinates;
    }

}
