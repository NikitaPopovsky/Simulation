package org.example.utils;

import org.example.models.EntityFactory;
import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;

public final class ActionUtil {
    public static void spawn(Class<?> entityClass, GameMap gameMap, int maxCount) {
        int count = 1;
        while (count <= maxCount) {
            Coordinate coordinate = gameMap.getEmptyCoordinates();
            Entity entity = EntityFactory.create(entityClass);
            gameMap.addEntity(coordinate, entity);
            count++;
        }

    }
}
