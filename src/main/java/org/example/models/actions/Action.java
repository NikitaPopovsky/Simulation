package org.example.models.actions;

import org.example.dto.Coordinate;
import org.example.models.Entity;
import org.example.models.GameMap;
import org.example.models.creatures.Creature;
import org.example.models.creatures.Herbivore;
import org.example.models.creatures.Predator;
import org.example.models.textures.Grass;

import java.util.*;
import java.util.stream.Collectors;

//import static org.example.models.Entity.DistributeCoordinatesByType;

public abstract class Action {

    public abstract void make ();

    //public abstract void makeStep();

    public Action() {
    }

    public static void addInitActions(Queue<Action> Actions) {
        Actions.add(InitializationCreatures.getInstance());
    }

    public static void addTurnActions (Queue<Action> Actions) {
        Actions.add(InteractToTarget.getInstance());
        Actions.add(MovingToTarget.getInstance());
    }

    //Преобразовываем список координат в список позиций
    protected Set<Integer> convertCoordinateToSet(List<Coordinate> coordinates) {
        return coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
    }

    public void makeStepCreature(Action action) {
        GameMap gameMap = GameMap.getInstance();
        Set<Coordinate> coordinates = gameMap.getCoordinates();
        Set<Coordinate> creatures = coordinates.stream()
                .filter(coordinate -> coordinate.getEntity() instanceof Creature).collect(Collectors.toSet());

        for (Coordinate coordinate : creatures) {
            Set<Integer> allEntity = coordinates.stream().map(Coordinate::getPosition).collect(Collectors.toSet());
            Set<Integer> targetPositions = getTargets(coordinate.getEntity(), coordinates);

            if (action instanceof MovingToTarget) {
                ((MovingToTarget) action).move(coordinate, allEntity, targetPositions);
            } else if (action instanceof InteractToTarget) {
                ((InteractToTarget) action).interact(coordinate, targetPositions);
            }

        }
        gameMap.setCoordinates(coordinates);


    }

    private Set<Integer> getTargets(Entity entity, Set<Coordinate> coordinates) {
        if (entity instanceof Herbivore) {
            return coordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Grass)
                    .map(Coordinate::getPosition)
                    .collect(Collectors.toSet());
        } else if (entity instanceof Predator) {
            return coordinates.stream()
                    .filter(coordinate -> coordinate.getEntity() instanceof Herbivore)
                    .map(Coordinate::getPosition)
                    .collect(Collectors.toSet());
        } else {
            return Set.of();
        }
    }

}
