package org.example.models;

import org.example.enums.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class GameMap {
    private static GameMap instance;
    private final HashMap<Coordinates, Entity> entities;
    private final int width;
    private final int height;

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public GameMap() {
        this.width = Constants.WIDTH_MAP.getValue();
        this.height = Constants.HEIGHT_MAP.getValue();
        this.entities = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Coordinates getEmptyCoordinates() {
        Random random = new Random();
        Coordinates coordinates = null;
        do {
            int x = getRandomNum(random, width);
            int y = getRandomNum(random, height);
            coordinates = new Coordinates(x, y);
        } while (entities.containsKey(coordinates));

        return coordinates;
    }

    private int getRandomNum(Random random, int maxNum) {
        return random.nextInt(1,maxNum + 1);
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entities.put(coordinates, entity);
    }

    public int getCountEntityByClass (Class<? extends Entity> entityClass) {
        return (int) entities.values().stream().filter(entityClass::isInstance).count();
    }

    public Map<Coordinates, Entity> getEntityByClass (Class<? extends Entity> entityClass) {
        return entities.entrySet().stream()
                .filter(entry-> entityClass.isInstance(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Set<Coordinates> getAllCoordinates () {
        return entities.keySet();
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = entities.get(from);

        if (entity == null) {
            return;
        }

        removeEntity(from);
        addEntity(to, entity);
    }

    public void removeEntity(Coordinates from) {
        entities.remove(from);
    }
}


