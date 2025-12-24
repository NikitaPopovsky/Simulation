package org.example.models;

import org.example.models.creatures.Creature;

import java.util.*;
import java.util.stream.Collectors;

public class GameMap {
    private final HashMap<Coordinate, Entity> entities;
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Coordinate getEmptyCoordinates() {
        Random random = new Random();
        Coordinate coordinate = null;
        do {
            int x = getRandomNum(random, width);
            int y = getRandomNum(random, height);
            coordinate = new Coordinate(x, y);
        } while (entities.containsKey(coordinate));

        return coordinate;
    }

    public void addEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

    public int getCountEntityByClass (Class<? extends Entity> entityClass) {
        return (int) entities.values().stream().filter(entityClass::isInstance).count();
    }

    public Map<Coordinate, Entity> getEntityByClass (Class<? extends Entity> entityClass) {
        return entities.entrySet().stream()
                .filter(entry-> entityClass.isInstance(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<Coordinate> getAllCoordinates () {
        return entities.keySet();
    }

    public void moveEntity(Coordinate from, Coordinate to) {
        Entity entity = entities.get(from);

        if (entity == null) {
            return;
        }

        removeEntity(from);
        addEntity(to, entity);
    }

    public void removeEntity(Coordinate coordinate) {
        entities.remove(coordinate);
    }

    public void clearBusyStatus() {
        for (Map.Entry <Coordinate, Entity> entityEntry : getEntityByClass(Creature.class).entrySet())  {
            Creature creature = (Creature) entityEntry.getValue();
            creature.clearBusy();
        }
    }

    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    private int getRandomNum(Random random, int maxNum) {
        return random.nextInt(1,maxNum + 1);
    }
}


