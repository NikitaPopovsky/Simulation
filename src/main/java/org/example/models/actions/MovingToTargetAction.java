package org.example.models.actions;

import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Creature;
import org.example.utils.BreadthFirstSearch;

import java.util.*;

public class MovingToTargetAction implements Action {

    public MovingToTargetAction() {
    }

    @Override
    public void make(GameMap gameMap) {
        Map<Coordinate, Entity> entities = gameMap.getEntityByClass(Creature.class);

        for (Map.Entry<Coordinate, Entity> entity : entities.entrySet()) {
            Creature creature = (Creature) entity.getValue();

            if (creature.isBusy()) {
                continue;
            }

            Class <? extends Entity> targetClass = creature.getTargetClass();
            Set<Coordinate> targets = gameMap.getEntityByClass(targetClass).keySet();

            Deque<Coordinate> path = BreadthFirstSearch.find(entity.getKey(), targets, gameMap);

            if (path.isEmpty()) {
                continue;
            }

            int countStep = creature.getSpeed();
            Coordinate step = null;
            if (countStep != 0 || path.isEmpty()) {
                step = path.pollLast();
            }
            gameMap.moveEntity(entity.getKey(), step);
        }
    }

}

