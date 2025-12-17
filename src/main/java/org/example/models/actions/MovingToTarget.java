package org.example.models.actions;

import org.example.models.Coordinates;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Creature;
import org.example.utils.BreadthFirstSearch;

import java.util.*;

public class MovingToTarget extends Action {
    private static MovingToTarget instance;

    public static MovingToTarget getInstance() {
        if (instance == null) {
            instance = new MovingToTarget();
        }
        return instance;
    }

    @Override
    public void make(GameMap gameMap) {
        Map<Coordinates, Entity> entities = gameMap.getEntityByClass(Creature.class);

        for (Map.Entry<Coordinates, Entity> entity : entities.entrySet()) {
            Creature creature = (Creature) entity.getValue();
            Class <? extends Entity> targetClass = creature.getTargetClass();
            Set<Coordinates> targets = gameMap.getEntityByClass(targetClass).keySet();

            Deque<Coordinates> path = BreadthFirstSearch.find(entity.getKey(), targets, gameMap);

            if (!path.isEmpty()) {
                gameMap.moveEntity(entity.getKey(), path.pollLast());
            }
        }
    }

}

