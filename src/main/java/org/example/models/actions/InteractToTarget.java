package org.example.models.actions;

import org.example.models.Coordinates;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.GameMapUtil;
import org.example.models.creatures.Creature;
import org.example.utils.BreadthFirstSearch;
import org.example.utils.Node;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InteractToTarget extends Action {

    private static InteractToTarget instance;

    public static InteractToTarget getInstance() {
        if (instance == null) {
            instance = new InteractToTarget();
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

            for (Coordinates neighbor: GameMapUtil.getNeighbors(entity.getKey())) {
                if (targets.contains(neighbor)) {
                    gameMap.removeEntity(neighbor);
                }
            }

        }
    }

}

