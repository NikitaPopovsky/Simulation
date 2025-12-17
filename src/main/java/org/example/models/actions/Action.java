package org.example.models.actions;

import org.example.models.Coordinates;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Action {

    public abstract void make (GameMap gameMap);

    public static void addInitActions(Queue<Action> Actions) {
        Actions.add(InitializationCreatures.getInstance());
    }

   public static void addTurnActions (Queue<Action> Actions) {
        Actions.add(ClearBusyStatus.getInstance());
        Actions.add(InteractToTarget.getInstance());
        Actions.add(MovingToTarget.getInstance());
        Actions.add(CreationEntity.getInstance());
    }

    protected Set<Coordinates> getCoordinates() {
        GameMap gameMap = GameMap.getInstance();
        return gameMap.getCoordinates();
    }

    protected void setCoordinates(Set<Coordinates> cordinates) {
        GameMap gameMap = GameMap.getInstance();
        gameMap.setCoordinates(cordinates);
    }

    protected Set<Integer> getTargets(Entity entity, Set<Coordinates> cordinates) {
        if (entity instanceof Herbivore) {
            return cordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Grass)
                    .map(Coordinates::getPosition)
                    .collect(Collectors.toSet());
        } else if (entity instanceof Predator) {
            return cordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Herbivore)
                    .map(Coordinates::getPosition)
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }

}
