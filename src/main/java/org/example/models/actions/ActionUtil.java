package org.example.models.actions;

import org.example.EntityFactory;
import org.example.models.Coordinates;
import org.example.models.Entity;
import org.example.models.GameMap;

public final class ActionUtil {
    public static void spawn(Class<?> entityClass, GameMap gameMap, int maxCount) {
        int count = 0;
        while (count <= maxCount) {
            Coordinates coordinates = gameMap.getEmptyCoordinates();
            Entity entity = EntityFactory.create(entityClass);
            gameMap.addEntity(coordinates, entity);
            count++;
        }

    }
}
