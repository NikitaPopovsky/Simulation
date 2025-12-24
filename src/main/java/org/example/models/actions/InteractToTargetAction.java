package org.example.models.actions;

import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.utils.GameMapUtil;
import org.example.models.creatures.Creature;

import java.util.Map;

public class InteractToTargetAction implements Action {

    @Override
    public void make(GameMap gameMap) {
        Map<Coordinate, Entity> entities = gameMap.getEntityByClass(Creature.class);

        for (Map.Entry<Coordinate, Entity> entity : entities.entrySet()) {
            Creature creature = (Creature) entity.getValue();
            Map<Coordinate, Entity> targets = gameMap.getEntityByClass(creature.getTargetClass());

            for (Coordinate neighbor: GameMapUtil.getNeighbors(entity.getKey())) {
                if (targets.containsKey(neighbor)) {
                    creature.attack(neighbor, gameMap);
                    creature.setBusy();
                }
            }

        }
    }

}

