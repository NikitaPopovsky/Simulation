package org.example.models.actions;

import org.example.models.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.utils.GameMapUtil;
import org.example.models.creatures.Creature;

import java.util.Map;
import java.util.Set;

public class InteractToTargetAction implements Action {

    @Override
    public void make(GameMap gameMap) {
        Map<Coordinate, Entity> entities = gameMap.getEntityByClass(Creature.class);

        for (Map.Entry<Coordinate, Entity> entity : entities.entrySet()) {
            Creature creature = (Creature) entity.getValue();
            Class <? extends Entity> targetClass = creature.getTargetClass();
            Map<Coordinate, Entity> targets = gameMap.getEntityByClass(targetClass);

            for (Coordinate neighbor: GameMapUtil.getNeighbors(entity.getKey())) {
                if (targets.containsKey(neighbor)) {

                    if (targets.get(neighbor) instanceof Creature) {
                        Predator attack = (Predator) creature;
                        Herbivore target = (Herbivore) targets.get(neighbor);

                        target.getDamage(attack.getPower());

                        if (target.getHealth() == 0) {
                            gameMap.removeEntity(neighbor);
                        }
                    } else {
                        gameMap.removeEntity(neighbor);
                    }

                    creature.setBusy();
                }
            }

        }
    }

}

