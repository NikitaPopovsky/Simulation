package org.example.models.actions;

import org.example.models.EntityFactory;
import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;

public final class ActionUtil {
    public static void spawn(Class<? extends Entity> entityClass, GameMap gameMap, int maxCount) {
        int count = 1;
        while (count <= maxCount) {
            Coordinate coordinate = gameMap.getEmptyCoordinates();
            Entity entity = EntityFactory.create(entityClass);
            gameMap.addEntity(coordinate, entity);
            count++;
        }

    }
}
